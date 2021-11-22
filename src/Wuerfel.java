public class Wuerfel {

    public static void main(String[] args) {
        int sides = Integer.parseInt(args[0]);
        int hit_counter = 0;
        int tried_count = 0;
        double total_hits = 0;
        double total_attempt_count = 0;
        boolean last_hit = false;
        while (total_attempt_count < Integer.parseInt(args[2])) {
            if ((Math.ceil(sides * Math.random())) == sides) {
                hit_counter = (last_hit || (tried_count == 0)) ? ++hit_counter : 1;
                last_hit = true;
                ++tried_count;
                continue;
            }
            ++tried_count;
            last_hit = false;

            if (hit_counter == Integer.parseInt(args[1]) - 1) { //-1 because it counts from zero i.e 0 1 2 3 4 is 5 hits
                total_attempt_count += tried_count;
                ++total_hits;
                tried_count = 0;
                hit_counter = 0;
            }
        }
        System.out.println(total_attempt_count / total_hits);
    }
}

