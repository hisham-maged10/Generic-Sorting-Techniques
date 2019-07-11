public enum SortingTechnique
{
    BUBBLE,INSERTION,SELECTION,MERGE,HEAP,QUICK;
    
    public static String getName(SortingTechnique st)
    {
        String name=st.toString();
        return name.charAt(0)+name.substring(1).toLowerCase()+" Sort";
    }
}