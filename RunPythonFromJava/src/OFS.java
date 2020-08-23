import java.io.File;
import java.io.FileOutputStream;

public class OFS {

	
	public static void StoreOFS()
	{
		String FromAddress = FetchData.getChangedData().get(0);
		String getDrAccount = "ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ="+FromAddress;

    try {
        File outputFile = new File("outagain.txt");
        FileOutputStream fileStream = new FileOutputStream(outputFile);

        String DrAccNo = FetchData.getChangedData().get(0);
		String TxnAmount = FetchData.getChangedData().get(3);
		String ToAddress = FetchData.getChangedData().get(1);
		String FinalFT = "\nENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ="+ToAddress +"\nENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ="+FromAddress+"\nFUNDS.TRANSFER,/I/PROCESS,AUTHOR/123456,,TRANSACTION.TYPE=AC,DEBIT.ACCT.NO=" + DrAccNo + ",DEBIT.CURRENCY=USD,DEBIT.AMOUNT=" + TxnAmount + ",CREDIT.ACCT.NO=" + DrAccNo + ",CREDIT.CURRENCY=USD";
        byte FinalFtString[] = FinalFT.getBytes();

        fileStream.write(FinalFtString);
    }catch(Exception e){
        System.out.println(e);
    }
	}

}
