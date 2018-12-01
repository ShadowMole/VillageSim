import java.util.Random;

public class RandomEnum {
    private static Gender[] genders = {Gender.FEMALE, Gender.MALE};
    private static HairStyle[] hairStyles = {HairStyle.CURLY, HairStyle.STRAIGHT, HairStyle.WAVY};
    private static HairColor[] hairColors = {HairColor.BLACK, HairColor.BLOND, HairColor.BROWN, HairColor.RED};
    private static EyeColor[] eyeColors = {EyeColor.BROWN, EyeColor.BLUE, EyeColor.GREEN, EyeColor.HAZEL};
    private static SkinColor[] skinColors = {SkinColor.BLACK, SkinColor.TAN, SkinColor.WHITE};

    private static Random rng = new Random();

    public static Gender getRandomGender(){
        return genders[rng.nextInt(genders.length)];
    }

    public static HairStyle getRandomHairStyles(){
        return hairStyles[rng.nextInt(hairStyles.length)];
    }

    public static HairColor getRandomHairColors(){
        return hairColors[rng.nextInt(hairColors.length)];
    }

    public static EyeColor getRandomEyeColors(){
        return eyeColors[rng.nextInt(eyeColors.length)];
    }

    public static SkinColor getRandomSkinColors(){
        return skinColors[rng.nextInt(skinColors.length)];
    }
}
