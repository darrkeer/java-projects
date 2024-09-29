package md2html;

public class Markup {
    private final String indexPref;
    private final String indexSuff;
    private final String repPref;
    private final String repSuff;

    public Markup(String indexPref, String indexSuff, String repPref, String repSuff){
        this.indexPref = indexPref;
        this.indexSuff = indexSuff;
        this.repPref = repPref;
        this.repSuff = repSuff;
    }

    private boolean compareString(String s, String cmp, int start){
        if (start + cmp.length() - 1 >= s.length())
            return false;
        return cmp.equals(s.substring(start, start + cmp.length()));
    }

    public boolean comparePrefix(String s, int start){
        return compareString(s, indexPref, start);
    }

    public boolean compareSuffix(String s, int start){
        return compareString(s, indexSuff, start);
    }

    public int writePrefix(StringBuilder sb) {
        sb.append(repPref);
        return indexPref.length();
    }

    public int writeSuffix(StringBuilder sb) {
        sb.append(repSuff);
        return indexSuff.length();
    }

    public String getIndexPrefix(){
        return indexPref;
    }

    public String getIndexSuffix(){
        return indexSuff;
    }
}
