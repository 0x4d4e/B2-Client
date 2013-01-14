package at.ac.tuwien.inso.esse.advancedsecsys.client.dto;

public class Entry
{
	private String name;
	private String telnr;

	public Entry(String paramString1, String paramString2)
	{
		this.name = paramString1;
		this.telnr = paramString2;
	}

	public String getName()
	{
		return this.name;
	}

	public String getTelnr()
	{
		return this.telnr;
	}

	public void setName(String paramString)
	{
		this.name = paramString;
	}

	public void setTelnr(String paramString)
	{
		this.telnr = paramString;
	}

	@Override
	public String toString() {
		return "Entry [name=" + name + ", telnr=" + telnr + "]";
	}


}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry
 * JD-Core Version:    0.6.2
 */