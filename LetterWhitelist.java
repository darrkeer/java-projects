public class LetterWhitelist implements CharacterWhitelist{
    @Override
    public boolean isWhitelistCharacter(char c){
        return Character.isAlphabetic(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}
