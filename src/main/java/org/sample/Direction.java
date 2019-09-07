package org.sample;

public enum Direction {
    NORTH(0, "N"),
    EAST(1, "E"),
    SOUTH(2, "S"),
    WEST(3, "W"),
    NORTH_EAST(4, "NE"),
    NORTH_WEST(5, "NW"),
    SOUTH_EAST(6, "SE"),
    SOUTH_WEST(7, "SW"),
    NONE(8, "None");

    private final int value;
    private final String shortName;

    public static final Direction[] ALL_VALID = {
            NORTH, WEST, SOUTH, EAST
    };

    private Direction(final int newValue, final String shortNameValue) {
        value = newValue;
        shortName = shortNameValue;
    }

    public static Direction getFromName(final String shortName) {
        for (Direction direction : Direction.values()) {
            if (direction.shortName.equalsIgnoreCase(shortName)) {
                return direction;
            }else{
                throw new IllegalArgumentException("direction string name non-existent");
            }
        }
        return Direction.NONE;
    }

    public static Direction getFromIndexNumber(final int number) {
        if (number > Direction.NONE.value) throw new IllegalArgumentException("direction index number non-existent");

        for (Direction direction : Direction.values()) {
            if (direction.value == number) {
                return direction;
            }
        }
        return Direction.NONE;
    }

    public static int getFromValue(final Direction directionValue) throws NoSuchFieldException {
        if (directionValue == null) throw new EnumConstantNotPresentException(Direction.class, directionValue.name());
        return directionValue.value;
    }

    //rotate counterclockwise 90 degrees (eg west to south)
    public  Direction turnLeft() {
        int index = (this.value + 3) % 4;
        return Direction.values()[index];
    }

    // rotate clockwise 90 degrees (eg north to east)
    public Direction turnRight() {
        int index = (this.value + 1) % 4;
        return Direction.values()[index];
    }

//assert equals  Location.getDirection(Location destination)
    public boolean hasDirection(Direction other)
    {
        switch (this)
        {
            case EAST: 			return other == EAST || other == NORTH_EAST || other == SOUTH_EAST;
            case NORTH:			return other == NORTH || other == NORTH_EAST || other == NORTH_WEST;
            case NORTH_EAST:	return other == NORTH_EAST || other == NORTH || other == EAST;
            case NORTH_WEST:	return other == NORTH_WEST || other == NORTH || other == WEST;
            case SOUTH:			return other == SOUTH || other == SOUTH_EAST || other == SOUTH_WEST;
            case SOUTH_EAST:	return other == SOUTH_EAST || other == SOUTH || other == EAST;
            case SOUTH_WEST:	return other == SOUTH_WEST || other == SOUTH || other == WEST;
            case WEST:			return other == WEST || other == NORTH_WEST || other == SOUTH_WEST;
            default: 			throw new IllegalArgumentException("Invalid direction");
        }
    }
}
