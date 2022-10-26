# SOLID Assignment with design pattern changes

In this exercise we improved some part(s) of the program using standard design patterns. 

# Changes

## Builder

An *IDocks* instance is now built using *DocksBuilder*.

## Factory

*ILoadableVehicle* instances are created using the static factory *LoadableVehicleFactory*.

## Strategy

Loadable vehicles implementations all implement the strategy interface ILoadableVehicle (ILoadable+IVecicle).
