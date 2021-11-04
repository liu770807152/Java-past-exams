package comp1110.exam;

public class Q3FamilyTree {
    /**
     * This function accepts an Individual <code>ancestor</code> representing
     * the root (common ancestor) of a family tree
     * and the name of a target individual to find within that family tree,
     * and returns a string representation of all the ancestors of that
     * individual, each separated by the string " born of ".
     * <p>
     * If target name matches the name of <code>ancestor</code>, then only
     * the target name is returned.
     * <p>
     * If the target name is not found within the family tree descended from
     * <code>ancestor</code>, then null is returned.
     * <p>
     * For example, given an Individual homer representing a person named
     * "Homer", who has children named "Lisa" and "Bart":
     * getAncestry(homer, "Lisa") returns "Lisa born of Homer";
     * getAncestry(homer, "Bart") returns "Bart born of Homer"; and
     * getAncestry(homer, "Homer") returns "Homer"; and
     * getAncestry(homer, "Barney") returns null
     * <p>
     * Note: each individual has only one parent in the family tree.
     *
     * @param ancestor   the root (common ancestor) of a family tree
     * @param targetName the name of an individual to find in the family tree
     * @return a String representing the ancestry of the individual named
     * <code>targetName</code>, or null if no such individual is found
     */
    public static String getAncestry(Individual ancestor, String targetName) {
        // BASE CASE: no child
        if (ancestor.children == null || ancestor.children.length == 0) {
            return null;
        }
        // case A: target name matches the name of ancestor
        if (ancestor.name.equals(targetName)) {
            return ancestor.name;
        }
        // case B: target name does not match the name of ancestor, but it could be ancestor's child
        for (Individual cur : ancestor.children) {
            if (cur.name.equals(targetName)) {
                return targetName + " born of " + ancestor.name;
            }
            String result = getAncestry(cur, targetName);
            if (result != null) {
                return result + " born of " + ancestor.name;
            }
        }
        return null;
    }

    /**
     * This class represents an individual with zero or more children.
     */
    static class Individual {
        public String name;
        /**
         * This individual's immediate descendants.
         * If this individual has no children, this field is null.
         */
        public Individual[] children;

        public Individual(String name, Individual[] children) {
            this.name = name;
            this.children = children;
        }
    }

    public static void main(String[] args) {
        Individual fatherA = new Individual("FatherA", new Individual[]{new Individual("SonA", null), new Individual("SonB", null)});
        Individual fatherB = new Individual("FatherB", new Individual[]{new Individual("DaughterA", null), new Individual("DaughterB", null)});
        Individual grandpa = new Individual("Grandpa", new Individual[]{fatherA, fatherB});
        System.out.println(getAncestry(grandpa, "SonB"));
        System.out.println(getAncestry(grandpa, "SonC"));
        System.out.println(getAncestry(grandpa, "Grandpa"));
    }
}
