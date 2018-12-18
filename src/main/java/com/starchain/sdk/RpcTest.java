package com.starchain.sdk;

import com.starchain.sdk.cryptography.Base58;
import com.starchain.sdk.info.AssetInfo;

public class RpcTest {
	
	public static void main(String[] args) {
//		byte[] privateKey = ECC.generateKey();
//		String s = Base58.encode(privateKey);
//		System.out.println(s);
//		System.out.println(Base58.decode(s));
		byte[] privateKey = Base58.decode("E8UDcvYZNJzm6CQsxkdD8dAZi4Log8pxfrvuYrynYsVj");
		Account account = new Account(privateKey);
		System.out.println(account.address);
		AssetInfo[] assetList = AccountAsset.getUpspent("http://47.52.44.156:25884", account);
		AssetInfo aInfo = null;
		for (AssetInfo assetInfo : assetList) {
			if (assetInfo.getAssetId().equals("e4aa1318a3b5b5e3949520daaead3b9e378414ded20a89b0f25561ba9203aac5")) {
				aInfo = assetInfo;
			}
		}
		String txData = Transaction.makeTransferTransaction(aInfo, account.publicKeyEncoded, "SeW7PXpvmpuCFYAHsmT49rqmHmh8n6WMpD", 0.001, "测试");
		String txIdString = SendTransfer.SignTxAndSend("http://47.52.44.156:25884",txData, account.publicKeyEncoded, privateKey);
		System.out.println(txIdString);
//		String string = DataUtil.bytesToHexString("测试".getBytes());
//		System.out.println(string);
//		System.out.println(new String(DataUtil.HexStringToByteArray(string)));
//		String hash = "ecf62caa8f9c9d37a91ab53c5fc7dd2b9f090ac01e82e5f069b6d104a4ea3d62";
	}

}
