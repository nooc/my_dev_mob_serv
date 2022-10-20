package space.nixus.truckdocking;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private final Scanner scanner;
    private final Docks docks;

    /**
     * Constructor Create docks.
     */
    private App() {
        // create scanner
        scanner = new Scanner(System.in);
        // create docks
        docks = new Docks();
        // add stations
        docks.addStation(new VanStation("A"));
        docks.addStation(new VanStation("B"));
        docks.addStation(new LightTruckStation("C"));
        // special case where HeavyTruck can dock here if cargo is below 9 tons.
        var special = new LightTruckStation("D");
        special.setSpecialCase((vehicle) -> vehicle instanceof HeavyTruck && vehicle.getTotalWeight() < 9000);
        docks.addStation(special);
        docks.addStation(new HeavyTruckStation("E"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    /**
     * Program loop.
     */
    private void run() {
        while (mainMenu()) {
        }
        System.out.println("\nBye.");
    }

    /**
     * Main menu
     *
     * @return false for exit
     */
    private boolean mainMenu() {
        System.out.println("""
                Truck docks 'Dumpa Mera':

                1. See docked vehicles.
                2. Register new vehicle for unloading.
                3. Exit

                """);
        var input = getInput("Choose");
        if (input.equals("1")) {
            PrintHelper.printDocksStatus(docks);
        } else if (input.equals("2")) {
            registerNewVehicle();
        } else return !input.equals("3");
        return true;
    }

    /**
     * Register new vehicle menu.
     */
    private void registerNewVehicle() {
        System.out.format("""
                Register new vehicle for unloading:

                1. %s
                2. %s
                3. %s

                """, Van.TYPE, LightTruck.TYPE, HeavyTruck.TYPE);
        // get type
        var vehicleType = getInput("   Type");
        // get weight
        int weight = Integer.parseInt(getInput(" Weight"));
        // add vehicle if valid weight
        LoadableVehicle vehicle;
        if (vehicleType.equals("1") && weight >= Van.UNLOADED_WEIGHT) {
            vehicle = new Van(weight);
        } else if (vehicleType.equals("2") && weight >= LightTruck.UNLOADED_WEIGHT) {
            vehicle = new LightTruck(weight);
        } else if (vehicleType.equals("3") && weight >= HeavyTruck.UNLOADED_WEIGHT) {
            vehicle = new HeavyTruck(weight);
        } else {
            System.out.format("""

                    Invalid weight:
                    Van >= %.0f kg
                    Light/Heavy truck >= %.0f kg

                    """, Van.UNLOADED_WEIGHT, LightTruck.UNLOADED_WEIGHT, HeavyTruck.UNLOADED_WEIGHT);
            return;
        }
        // fill cargo hold
        loadWithCargo(vehicle, weight - vehicle.getUnloadedWeight());
        // register
        if (docks.dockVehicle(vehicle)) {
            System.out.println("Vehicle registered.");
        } else {
            System.out.println("Could not dock vehicle. There may not be available spaces.");
        }
    }

    /**
     * Load vehicle up to specified amount. Trucks can take pallets and boxes while
     * vans can only take boxes. For trucks, any weight above 300kg will be loaded
     * as pallets containing boxes. Boxes will have a weight of max 100kg.
     *
     * @param vehicle     A LoadableVehicle
     * @param cargoWeight Cargo weight
     */
    private void loadWithCargo(LoadableVehicle vehicle, double cargoWeight) {
        // load truck
        if (vehicle instanceof Truck) {
            Pallet pallet = new Pallet();
            cargoWeight -= pallet.getWeight(); // subtract bare pallet weight
            // load truck with pallets while remaining cargo > 300kg
            while (cargoWeight > 300) {
                var box = new Box(100);
                cargoWeight -= box.getWeight();

                // pallet full? add to vehicle and set to null.
                if (!pallet.addBox(box)) {
                    vehicle.loadCargo(pallet);
                    pallet = new Pallet();
                    cargoWeight -= pallet.getWeight(); // subtract bare pallet weight
                    pallet.addBox(box); // add dangling box
                }
            }
            // load final pallet
            vehicle.loadCargo(pallet);
        }
        // load any remaining cargo as boxes
        while (cargoWeight > 0) {
            // 100kg boxes
            if (cargoWeight >= 100) {
                vehicle.loadCargo(new Box(100));
                cargoWeight -= 100;
            } else {
                // remaining box < 100kg
                vehicle.loadCargo(new Box(cargoWeight));
                break;
            }
        }
    }

    /**
     * Input prompt.
     *
     * @param prompt Prompt string
     * @return non empty string
     */
    private String getInput(String prompt) {
        while (true) {
            System.out.format("%s: ", prompt);
            var result = scanner.nextLine().strip();
            if (!result.isEmpty()) return result;
        }
    }
}
