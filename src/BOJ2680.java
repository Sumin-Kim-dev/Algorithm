import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2680 {
    static final char[] ALPHANEUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(decode(br.readLine())).append('\n');
        }
        System.out.println(sb);
    }

    String decode(String qr) {
        String datablocks = toBinary(qr);
        int index = 0;
        StringBuilder decode = new StringBuilder();
        int count = 0;
        while (index < datablocks.length()) {
            if (index + 4 >= datablocks.length()) break;
            int mode = Integer.parseInt(datablocks.substring(index, index + 4), 2);
            index += 4;
            if (mode == 1) {
                int currCount = Integer.parseInt(datablocks.substring(index, index + 10), 2);
                count += currCount;
                index += 10;
                while (currCount > 0) {
                    if (currCount == 2) {
                        int curr = Integer.parseInt(datablocks.substring(index, index + 7), 2);
                        decode.append(String.format("%02d", curr));
                        index += 7;
                        break;
                    }
                    if (currCount == 1) {
                        decode.append(Integer.parseInt(datablocks.substring(index, index + 4), 2));
                        index += 4;
                        break;
                    }
                    int curr = Integer.parseInt(datablocks.substring(index, index + 10), 2);
                    decode.append(String.format("%03d", curr));
                    index += 10;
                    currCount -= 3;
                }
                continue;
            }
            if (mode == 2) {
                int currCount = Integer.parseInt(datablocks.substring(index, index + 9), 2);
                count += currCount;
                index += 9;
                while (currCount > 0) {
                    if (currCount == 1) {
                        int curr = Integer.parseInt(datablocks.substring(index, index + 6), 2);
                        decode.append(ALPHANEUMERIC[curr]);
                        index += 6;
                        break;
                    }
                    int curr = Integer.parseInt(datablocks.substring(index, index + 11), 2);
                    decode.append(ALPHANEUMERIC[curr / 45]).append(ALPHANEUMERIC[curr % 45]);
                    index += 11;
                    currCount -= 2;
                }
                continue;
            }
            if (mode == 4) {
                int currCount = Integer.parseInt(datablocks.substring(index, index + 8), 2);
                count += currCount;
                index += 8;
                while (currCount > 0) {
                    int curr = Integer.parseInt(datablocks.substring(index, index + 8), 2);
                    index += 8;
                    if (curr < 0x20 || curr > 0x7e) {
                        decode.append('\\').append(String.format("%02X", curr));
                    } else {
                        if ((char) curr == '#') decode.append('\\');
                        decode.append((char) curr);
                    }
                    currCount--;
                }
                continue;
            }
            if (mode == 8) {
                int currCount = Integer.parseInt(datablocks.substring(index, index + 8), 2);
                count += currCount;
                index += 8;
                while (currCount > 0) {
                    int curr = Integer.parseInt(datablocks.substring(index, index + 13), 2);
                    if (curr >= 0x20 && curr <= 0x7e) {
                        if ((char) curr == '#') decode.append('\\');
                        decode.append((char) curr);
                    } else {
                        decode.append("#").append(String.format("%04X", curr));
                    }
                    index += 13;
                    currCount--;
                }
                continue;
            }
            break;
        }
        return count + " " + decode;
    }

    String toBinary(String qr) {
        StringBuilder sb = new StringBuilder();
        int curr;
        for (char c : qr.toCharArray()) {
            if (c >= '0' && c <= '9') {
                curr = c - '0';
            } else {
                curr = c - 'A' + 10;
            }
            sb.append(String.format("%04d", Integer.parseInt(Integer.toBinaryString(curr))));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        new BOJ2680().io();
    }
}
