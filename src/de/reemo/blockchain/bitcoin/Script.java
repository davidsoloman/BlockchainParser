package de.reemo.blockchain.bitcoin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class Script {
	static Map<Byte, String> opcodes;
	static {
		opcodes = new HashMap<>();
		opcodes.put((byte) 0x00, "OP_0");
		opcodes.put((byte) 0x4c, "OP_PUSHDATA1");
		opcodes.put((byte) 0x4d, "OP_PUSHDATA2");
		opcodes.put((byte) 0x4e, "OP_PUSHDATA4");
		opcodes.put((byte) 0x4f, "OP_1NEGATE");
		opcodes.put((byte) 0x50, "OP_RESERVED");
		opcodes.put((byte) 0x51, "OP_1");
		//opcodes.put((byte) 0x52, "OP_TRUE");
		opcodes.put((byte) 0x61, "OP_NOP");
		opcodes.put((byte) 0x63, "OP_IF");
		opcodes.put((byte) 0x64, "OP_NOTIF");
		opcodes.put((byte) 0x67, "OP_ELSE");
		opcodes.put((byte) 0x68, "OP_ENDIF");
		opcodes.put((byte) 0x69, "OP_VERIFY");
		opcodes.put((byte) 0x6a, "OP_RETURN");
		opcodes.put((byte) 0x6b, "OP_TOALTSTACK");
		opcodes.put((byte) 0x6c, "OP_FROMALTSTACK");
		opcodes.put((byte) 0x73, "OP_IFDUP");
		opcodes.put((byte) 0x74, "OP_DEPTH");
		opcodes.put((byte) 0x75, "OP_DROP");
		opcodes.put((byte) 0x76, "OP_DUP");
		opcodes.put((byte) 0x77, "OP_NIP");
		opcodes.put((byte) 0x78, "OP_OVER");
		opcodes.put((byte) 0x79, "OP_PICK");
		opcodes.put((byte) 0x7a, "OP_ROLL");
		opcodes.put((byte) 0x7b, "OP_ROT");
		opcodes.put((byte) 0x7c, "OP_SWAP");
		opcodes.put((byte) 0x7d, "OP_TUCK");
		opcodes.put((byte) 0x6d, "OP_2DROP");
		opcodes.put((byte) 0x6e, "OP_2DUP");
		opcodes.put((byte) 0x6f, "OP_3DUP");
		opcodes.put((byte) 0x70, "OP_2OVER");
		opcodes.put((byte) 0x71, "OP_2ROT");
		opcodes.put((byte) 0x72, "OP_2SWAP");
		opcodes.put((byte) 0x7e, "OP_CAT");
		opcodes.put((byte) 0x7f, "OP_SUBSTR");
		opcodes.put((byte) 0x80, "OP_LEFT");
		opcodes.put((byte) 0x81, "OP_RIGHT");
		opcodes.put((byte) 0x82, "OP_SIZE");
		opcodes.put((byte) 0x83, "OP_INVERT");
		opcodes.put((byte) 0x84, "OP_AND");
		opcodes.put((byte) 0x85, "OP_OR");
		opcodes.put((byte) 0x86, "OP_XOR");
		opcodes.put((byte) 0x87, "OP_EQUAL");
		opcodes.put((byte) 0x88, "OP_EQUALVERIFY");
		opcodes.put((byte) 0x8b, "OP_1ADD");
		opcodes.put((byte) 0x8c, "OP_1SUB");
		opcodes.put((byte) 0x8d, "OP_2MUL");
		opcodes.put((byte) 0x8e, "OP_2DIV");
		opcodes.put((byte) 0x8f, "OP_NEGATE");
		opcodes.put((byte) 0x90, "OP_ABS");
		opcodes.put((byte) 0x91, "OP_NOT");
		opcodes.put((byte) 0x92, "OP_0NOTEQUAL");
		opcodes.put((byte) 0x93, "OP_ADD");
		opcodes.put((byte) 0x94, "OP_SUB");
		opcodes.put((byte) 0x95, "OP_MUL");
		opcodes.put((byte) 0x96, "OP_DIV");
		opcodes.put((byte) 0x97, "OP_MOD");
		opcodes.put((byte) 0x98, "OP_LSHIFT");
		opcodes.put((byte) 0x99, "OP_RSHIFT");
		opcodes.put((byte) 0x9a, "OP_BOOLAND");
		opcodes.put((byte) 0x9b, "OP_BOOLOR");
		opcodes.put((byte) 0x9c, "OP_NUMEQUAL");
		opcodes.put((byte) 0x9d, "OP_NUMEQUALVERIFY");
		opcodes.put((byte) 0x9e, "OP_NUMNOTEQUAL");
		opcodes.put((byte) 0x9f, "OP_LESSTHAN");
		opcodes.put((byte) 0xa0, "OP_GREATERTHAN");
		opcodes.put((byte) 0xa1, "OP_LESSTHANOREQUAL");
		opcodes.put((byte) 0xa2, "OP_GREATERTHANOREQUAL");
		opcodes.put((byte) 0xa3, "OP_MIN");
		opcodes.put((byte) 0xa4, "OP_MAX");
		opcodes.put((byte) 0xa5, "OP_WITHIN");
		opcodes.put((byte) 0xa6, "OP_RIPEMD160");
		opcodes.put((byte) 0xa7, "OP_SHA1");
		opcodes.put((byte) 0xa8, "OP_SHA256");
		opcodes.put((byte) 0xa9, "OP_HASH160");
		opcodes.put((byte) 0xaa, "OP_HASH256");
		opcodes.put((byte) 0xab, "OP_CODESEPARATOR");
		opcodes.put((byte) 0xac, "OP_CHECKSIG");
		opcodes.put((byte) 0xad, "OP_CHECKSIGVERIFY");
		opcodes.put((byte) 0xae, "OP_CHECKMULTISIG");
		opcodes.put((byte) 0xaf, "OP_CHECKMULTISIGVERIFY");
		opcodes.put((byte) 0xfd, "OP_PUBKEYHASH");
		opcodes.put((byte) 0xfe, "OP_PUBKEY");
		opcodes.put((byte) 0xff, "OP_INVALIDOPCODE");
		opcodes.put((byte) 0x50, "OP_RESERVED");
		opcodes.put((byte) 0x62, "OP_VER");
		opcodes.put((byte) 0x65, "OP_VERIF");
		opcodes.put((byte) 0x66, "OP_VERNOTIF");
		opcodes.put((byte) 0x89, "OP_RESERVED1");
		opcodes.put((byte) 0x8a, "OP_RESERVED2");
		opcodes.put((byte) 0xb0, "OP_NOP1");
		opcodes.put((byte) 0xb1, "OP_NOP2");
		opcodes.put((byte) 0xb2, "OP_NOP3");
		opcodes.put((byte) 0xb3, "OP_NOP4");
		opcodes.put((byte) 0xb4, "OP_NOP5");
		opcodes.put((byte) 0xb5, "OP_NOP6");
		opcodes.put((byte) 0xb6, "OP_NOP7");
		opcodes.put((byte) 0xb7, "OP_NOP8");
		opcodes.put((byte) 0xb8, "OP_NOP9");
	}

	public static String scriptToString(byte[] byteScript) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteScript.length; i++) {
			if (byteScript[i] >= ((byte) 0x01)
					&& byteScript[i] <= ((byte) 0x4b)) {
				int length = byteScript[i] > 0 ? byteScript[i]
						: byteScript[i] + 256;
				byte[] buffer = new byte[length];
				if(length+i > byteScript.length-1) { 
					length = byteScript.length-i-1;
					sb.append("TRUNCATED("+(length+i-byteScript.length)+") ");
				}
				System.arraycopy(byteScript, i + 1, buffer, 0, length-1);
				sb.append("0x").append(Hex.encodeHex(buffer)).append(" ");
				i += length;
			} else if (byteScript[i] == ((byte) 0x4c)) {
				byte[] buffer = new byte[] { byteScript[i + 1] };
				sb.append("0x").append(Hex.encodeHex(buffer)).append(" ");
				i++;
			} else if (byteScript[i] == ((byte) 0x4d)) {
				byte[] buffer = new byte[] { byteScript[i + 1],
						byteScript[i + 2] };
				sb.append("0x").append(Hex.encodeHex(buffer)).append(" ");
				i += 2;
			} else if (byteScript[i] == ((byte) 0x4e)) {
				byte[] buffer = new byte[] { byteScript[i + 1],
						byteScript[i + 2], byteScript[i + 3], byteScript[i + 4] };
				sb.append("0x").append(Hex.encodeHex(buffer)).append(" ");
				i += 4;
			} else if (opcodes.containsKey(byteScript[i])) {
				sb.append(opcodes.get(byteScript[i])).append(" ");
			} else {
				byte[] buffer = new byte[] { byteScript[i] };
				sb.append("0x").append(Hex.encodeHex(buffer)).append("! ");
			}
		}
		return sb.toString();
	}
}
