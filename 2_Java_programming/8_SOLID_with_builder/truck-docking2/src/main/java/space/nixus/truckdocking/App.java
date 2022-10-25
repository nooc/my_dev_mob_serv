package space.nixus.truckdocking;

import java.util.Scanner;
import space.nixus.truckdocking.builders.DocksBuilder;
import space.nixus.truckdocking.factories.LoadableVehicleFactory;
import space.nixus.truckdocking.models.Box;
import space.nixus.truckdocking.models.IDocks;
import space.nixus.truckdocking.models.ILoadableVehicle;
import space.nixus.truckdocking.models.Pallet;
import space.nixus.truckdocking.models.VehicleType;

/**
 * Hello world!
 */
public class App {
    private final Scanner scanner;
    private final IDocks docks;

    /**
     * Constructor Create docks.
     */
    private App() {
        // create scanner
        scanner = new Scanner(System.in);
        // create docks
        docks = new DocksBuilder()
            .addVanStation("A")
            .addVanStation("B")
            .addLightTruckStation("C")
            .addLightTruckStation("D")
            .addHeavyTruckStation("E")
            .addSpecialCase("D", (vehicle) -> vehicle.getType().equals(VehicleType.HeavyTruck) && vehicle.getTotalWeight() < 9000)
            .build();
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
                0. QUIT

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
        var types = VehicleType.values();
        System.out.format("""
                Register new vehicle for unloading:

                1. %s
                2. %s
                3. %s
                4. BACK

                """, types[0], types[1], types[2]);
        // get type
        var vehicleType = getInt("   Type", 1, 4);
        if(vehicleType==4) {
            return;
        }
        // get weight
        int weight = getInt(" Weight", VehicleType.Van.WEIGHT, 40000);
        // add vehicle if valid weight
        ILoadableVehicle vehicle = LoadableVehicleFactory.createVehicle(VehicleType.values()[vehicleType], weight);
        // if not valid, print error
        if (vehicle==null) {
            System.out.println("\nWeight must equal or exceed unloaded weight:");
            for(var type : VehicleType.values()) {
                System.out.format("%s >= %d kg\n", type.LABEL, type.WEIGHT);
            }
            System.out.println();
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
    private void loadWithCargo(ILoadableVehicle vehicle, double cargoWeight) {
        // load truck
        if (!vehicle.getType().equals(VehicleType.Van)) {
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

    /**
     * Int input prompt.
     * @param prompt Prompt string
     * @param min minimum value
     * @param max maximum value
     * @return integer within range min,max
     */
    private int getInt(String prompt, int min, int max) {
        while(true) {
            try {
                var value = Integer.parseInt(getInput(prompt));
                if(value>=min && value<=max) {
                    return value;
                }
            }
            finally {}
        }
    }
}
