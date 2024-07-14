package programmers;

public class 동영상재생기 {
    int N;

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int full_time = convertTimeStringToInt(video_len);
        int current_time = convertTimeStringToInt(pos);
        int op_start_time = convertTimeStringToInt(op_start);
        int op_end_time = convertTimeStringToInt(op_end);

        for (String command : commands) {
            if (op_start_time <= current_time && current_time < op_end_time) {
                current_time = op_end_time;
            }

            if (command.equals("next")) {
                current_time += 10;
                if (full_time < current_time) current_time = full_time;
            } else {
                current_time -= 10;
                if (current_time < 0) current_time = 0;
            }


        }

        if (op_start_time <= current_time && current_time < op_end_time) {
            current_time = op_end_time;
        }

        return convertTimeIntToString(current_time);
    }

    int convertTimeStringToInt(String time_str) {
        return Integer.parseInt(time_str.substring(0, 2)) * 60 + Integer.parseInt(time_str.substring(3));
    }

    String convertTimeIntToString(int time) {
        String time_str;
        if (time / 60 < 10) {
            time_str = "0" + time / 60 + ":";
        } else {
            time_str = time / 60 + ":";
        }

        if (time % 60 < 10) {
            time_str += "0" + time % 60;
        } else {
            time_str += time % 60;
        }

        return time_str;
    }
}