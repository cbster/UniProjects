public class Wuerfel {
    static double throw_dice(int a, int b, int c){
        int sides = a;
        int to_win = b;
        int no_attempts = c;
        int hit_counter = 0;
        int tried_count = 0;
        double total_hits = 0;
        double total_attempt_count = 0;
        double average_attempts = 0;
        boolean last_hit = false;
        while (total_attempt_count < no_attempts) {
            double result = (Math.ceil(sides * Math.random()));
            if (result == sides) {
                if (last_hit || (tried_count == 0)) {
                    ++hit_counter;
                    last_hit = true;
                    ++tried_count;
                    continue;
                } else {
                    hit_counter = 1;
                    last_hit = true;
                    ++tried_count;
                    continue;
                }
            }
            ++tried_count;
            last_hit = false;

            if (hit_counter == to_win-1) { //-1 because it counts from zero i.e 0 1 2 3 4 is 5 hits
                total_attempt_count += tried_count;
                ++total_hits;
                tried_count = 0;
                hit_counter = 0;
                last_hit = false;
            }
        }
        average_attempts = total_attempt_count / total_hits;
        System.out.println(average_attempts);
        return average_attempts;
    }

    public static void main(String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        throw_dice(a, b ,c);
    }
}

