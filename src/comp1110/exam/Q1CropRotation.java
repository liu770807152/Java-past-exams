package comp1110.exam;

import java.util.*;

public class Q1CropRotation {
    /**
     * Each Vegetable is assigned to one of four groups.
     */
    public enum Group {
        LEGUME, BRASSICA, ALLIUM, FRUITING
    }

    public static class Vegetable {
        String name;
        Group group;

        public Vegetable(String name, Group group) {
            this.name = name;
            this.group = group;
        }

        @Override
        public String toString() {
            return name + " (" + group.name().toLowerCase() + ")";
        }
    }

    /**
     * Get all valid crop rotations that can be composed from the provided
     * set of vegetable crops for the given number of seasons.
     * A crop rotation is a sequence of vegetable crops to plant.
     *
     * One crop is planted per season, and any crop may be planted at most once.
     * Crops must be planted in order of their Group according to the following
     * rules:
     * - a LEGUME may only be followed by a BRASSICA;
     * - a BRASSICA may only be followed by an ALLIUM;
     * - an ALLIUM may only be followed by a FRUITING crop; and
     * - a FRUITING crop may only be followed by a LEGUME.
     * <p>
     * For example, the call
     * getAllRotations([Tomato (fruiting), Onion (allium)], 2)
     * returns a set containing a single rotation:
     * - [Onion (allium), Tomato (fruiting)]
     * because an ALLIUM may only be followed by a fruiting crop.
     * <p>
     * The call
     * getAllRotations([Tomato (fruiting), Kale (brassica), Onion (allium), Pea (legume)], 4)
     * returns a set containing four rotations:
     * - [Kale (brassica), Onion (allium), Tomato (fruiting), Pea (legume)]
     * - [Onion (allium), Tomato (fruiting), Pea (legume), Kale (brassica)]
     * - [Pea (legume), Kale (brassica), Onion (allium), Tomato (fruiting)]
     * - [Tomato (fruiting), Pea (legume), Kale (brassica), Onion (allium)]
     * <p>
     * If no valid crop rotation can be found, an empty list is returned.
     * For example, the call:
     * getAllRotations([Tomato (fruiting), Gai Lan (brassica)], 2)
     * returns an empty set, because a FRUITING crop cannot be followed by
     * a BRASSICA, and a BRASSICA cannot be followed by a FRUITING crop.
     * LEGUME -> BRASSICA -> ALLIUM -> FRUITING -> LEGUME
     *
     * @param crops   the set of vegetable crops from which to construct a rotation
     * @param seasons the number of seasons
     * @return the set of all possible rotations of the provided crops for the
     * given number of seasons.  If there are no crops or no seasons or the number
     * of seasons is greater than the number of crops, return empty list.
     */
    public static Set<List<Vegetable>> getAllRotations(Set<Vegetable> crops, int seasons) {
        ArrayList<Vegetable> used = new ArrayList<>();          // vegetables used so far in a given search
        Set<List<Vegetable>> rotations = new HashSet<>();  // rotations found so far

        /* If there are no crops or no seasons or the number of seasons is
           greater than the number of crops, return an empty list. */
        if (crops.size() == 0 || seasons == 0) {
            return new HashSet<>(new LinkedList<>());
        }
        for (Vegetable cur : crops) {
            // step0: used为空时加入当前vegetable
            used.add(cur);
            for (int i = 1; i < seasons; i++) {
                // 优化代码
                // int listSize = used.size();
                Vegetable lastIn = used.get(used.size() - 1);
                // step1: 遍历剩余长度的次数，看看有没有vegetable能加入used
                for (Vegetable v : crops) {
                    if (canFollow(lastIn, v)) {
                        used.add(v);
                    }
                }
               // crops.forEach((Vegetable v) -> {
               //     if (canFollow(lastIn, v)) {
               //         used.add(v);
               //     }
               // });
                // 优化代码：大小不变，说明没加进去，也就是没找到下一个vegetable
                //if (listSize == used.size()) {
                //    break;
                //}
            }
            // step2: 看看加完后的used是否能加入rotations
            if (used.size() == seasons) {
                rotations.add((List<Vegetable>) used.clone());
            }
            // step3: 当前的used用过了，应该清空
            used.clear();
        }
        return rotations;
    }

    /**
     * Recursive method to find all rotations given a starting crop.
     * LEGUME -> BRASSICA -> ALLIUM -> FRUITING -> LEGUME
     *
     * @param crops as above
     * @param seasons as above
     * @param used crops already used (in the order in which they are used)
     * @param rotations all rotations found so far.
     */
    private static void getFixedRotation(Set<Vegetable> crops, int seasons, List<Vegetable> used,
                                         Set<List<Vegetable>> rotations) {
        // BASE CASE: nothing in crops
        if (crops.size() == 0) {
            return;
        }
        for (Vegetable cur : crops) {
            // step0: used为空时加入当前vegetable
            if (used.size() == 0) {
                used.add(cur);
            } else {
                // step1: 看看当前的vegetable能不能加入used
                Vegetable lastIn = used.get(used.size() - 1);
                if (canFollow(lastIn, cur)) {
                    used.add(cur);
                }
            }
            // step2: 看看加完后的used是否能加入rotations
            if (used.size() == seasons) {
                ArrayList<Vegetable> usedClone = new ArrayList<>(used);
                rotations.add(usedClone);
            }
            // step3: 得到去掉当前元素的子集，递归这个子集
            Set<Vegetable> smallerCrops = new HashSet<>(crops);
            smallerCrops.remove(cur);
            getFixedRotation(smallerCrops, seasons, used, rotations);
            // step4: 当前的元素用过了，应该在used中移除
            used.remove(cur);
        }
    }

    /**
     * Determine whether one vegetable can follow another
     * LEGUME -> BRASSICA -> ALLIUM -> FRUITING -> LEGUME
     *
     * @param first the first vegetable
     * @param next the next vegetable
     * @return true if next can follow first
     */
    private static boolean canFollow(Vegetable first, Vegetable next) {
        if (first.group == Group.LEGUME & next.group == Group.BRASSICA) {
            return true;
        } else if (first.group == Group.BRASSICA & next.group == Group.ALLIUM) {
            return true;
        } else if (first.group == Group.ALLIUM & next.group == Group.FRUITING) {
            return true;
        } else if (first.group == Group.FRUITING & next.group == Group.LEGUME) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Set<Vegetable> testA = new HashSet<>(){{
            add(new Vegetable("Tomato", Group.FRUITING));
            add(new Vegetable("Kale", Group.BRASSICA));
            add(new Vegetable("Onion", Group.ALLIUM));
            add(new Vegetable("Pea", Group.LEGUME));
        }};
        Set<Vegetable> testB = new HashSet<>(){{
            add(new Vegetable("Tomato", Group.FRUITING));
            add(new Vegetable("Gai Lan", Group.BRASSICA));
        }} ;
        Set<List<Vegetable>> rotations = new HashSet<>();
        getFixedRotation(testA, 4, new ArrayList<>(), rotations);
        System.out.println("getAllRotationsA: " + getAllRotations(testA, 4));
        System.out.println("getFixedRotationA: " + rotations);

        rotations.clear();
        getFixedRotation(testB, 2, new ArrayList<>(), rotations);
        System.out.println("getAllRotationsB: " + getAllRotations(testB, 2));
        System.out.println("getFixedRotationB: " + rotations);
    }
}