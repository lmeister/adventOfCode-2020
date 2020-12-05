package de.leonm.adventofcode20.models;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    /*
        Map is filled with the given passport data
        byr - Birth Year
        iyr - Issue Year
        eyr - Expiration year
        hgt - Height
        hcl - Hair Color
        ecl - Eye Color
        pid - Passport ID
        cid - Country ID
     */
    private HashMap<String, String> data;
    private Set<String> eyeColors;

    public Passport() {
        data = new HashMap<>();
        eyeColors = new HashSet<>();
        eyeColors.addAll(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
    }

    public void addData(String data) {
        String[] splitData = data.split(" ");
        for (String s : splitData) {
            String[] keyValue = s.split(":");
                this.data.put(keyValue[0], keyValue[1]);
        }
    }

    public boolean isValidPassport(boolean strict) {
        return (isByrValid(strict) && isIyrValid(strict) && isEyrValid(strict) &&
                isHgtValid(strict) && isHclValid(strict) && isEclValid(strict) && isPidValid(strict));
    }

    private boolean isByrValid(boolean strict) {
        if (data.get("byr") != null) {
            if (strict) {
                int val = Integer.parseInt(data.get("byr"));
                return val >= 1920 && val <= 2002;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isIyrValid(boolean strict) {
        if (data.get("iyr") != null) {
            if (strict) {
                int val = Integer.parseInt(data.get("iyr"));
                return val >= 2010 && val <= 2020;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isEyrValid(boolean strict) {
        if (!(data.get("eyr") == null)) {
            if (strict) {
                int val = Integer.parseInt(data.get("eyr"));
                return val >= 2020 && val <= 2030;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isHgtValid(boolean strict) {
        if (data.get("hgt") != null) {
            Pattern pattern = Pattern.compile("(?<value>\\d+)(?<unit>cm|in)");
            Matcher matcher = pattern.matcher(data.get("hgt"));

            if (strict) {
                if (matcher.find()) {
                    String unit = matcher.group("unit");
                    int height = Integer.parseInt(matcher.group("value"));
                    if (unit.equals("cm")) {
                        return height >= 150 && height <= 193;
                    } else {
                        return height >= 59 && height <= 76;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isHclValid(boolean strict) {
        if (data.get("hcl") != null) {
            if (strict) {
                Pattern pattern = Pattern.compile("#(?<value>[a-fA-F0-9]{6})");
                Matcher matcher = pattern.matcher(data.get("hcl"));
                return matcher.find();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isEclValid(boolean strict) {
        if (data.get("ecl") != null) {
            if (strict) {
                return eyeColors.contains(data.get("ecl"));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isPidValid(boolean strict) {
        if (data.get("pid") != null) {
            if (strict) {
                return data.get("pid").length() == 9;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Passport{" +
                "data=" + data.toString() +
                '}';
    }
}
