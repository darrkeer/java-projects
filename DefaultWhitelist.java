public class DefaultWhitelist implements CharacterWhitelist{
    @Override
    public boolean isWhitelistCharacter(char c){
        return !Character.isWhitespace(c);
    }
}