package civ.Model;

/**
 * Created by miku on 02/06/2017.
 * TODO: redo as a HashMap<Economy,Tool>, ask help from Martin
 */
public class Tool {

    enum Farming {
        Plow,
        Harvester,
        Spray,
        GeneticModifications,
        Vertical
    }

    enum Computer {
        Electronics,
        Electronic,
        Photonic,
        Database,
        Server,
        Robot,
        Fabricator,
        Drone,
        MachineLearner,
        Bot
    }

    enum Media {
        PrintingPress,
        BroadcastingStation,
        Camera,
        SocialMedia
    }

    enum Transport {
        Stirrups,
        Ship,
        Trains,
        Cars,
        Airplanes,
        Rocket,
        Satellite
    }

    enum Weapon{
        Sword,
        Guns,
        Rifles,
        MachineGuns,
        Cannons,
        Nukes
    }

}
