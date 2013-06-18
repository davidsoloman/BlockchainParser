package de.reemo.blockchain.namecoin;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import de.reemo.blockchain.BlockInputStreamReader;

public class Script {
	public final static Byte OP_0 = (byte) 0x00;
	public final static Byte OP_PUSHDATA1 = (byte) 0x4c;
	public final static Byte OP_PUSHDATA2 = (byte) 0x4d;
	public final static Byte OP_PUSHDATA4 = (byte) 0x4e;
	public final static Byte OP_1NEGATE = (byte) 0x4f;
	public final static Byte OP_RESERVED = (byte) 0x50;
	public final static Byte OP_1 = (byte) 0x51;
	public final static Byte OP_NOP = (byte) 0x61;
	public final static Byte OP_IF = (byte) 0x63;
	public final static Byte OP_NOTIF = (byte) 0x64;
	public final static Byte OP_ELSE = (byte) 0x67;
	public final static Byte OP_ENDIF = (byte) 0x68;
	public final static Byte OP_VERIFY = (byte) 0x69;
	public final static Byte OP_RETURN = (byte) 0x6a;
	public final static Byte OP_TOALTSTACK = (byte) 0x6b;
	public final static Byte OP_FROMALTSTACK = (byte) 0x6c;
	public final static Byte OP_IFDUP = (byte) 0x73;
	public final static Byte OP_DEPTH = (byte) 0x74;
	public final static Byte OP_DROP = (byte) 0x75;
	public final static Byte OP_DUP = (byte) 0x76;
	public final static Byte OP_NIP = (byte) 0x77;
	public final static Byte OP_OVER = (byte) 0x78;
	public final static Byte OP_PICK = (byte) 0x79;
	public final static Byte OP_ROLL = (byte) 0x7a;
	public final static Byte OP_ROT = (byte) 0x7b;
	public final static Byte OP_SWAP = (byte) 0x7c;
	public final static Byte OP_TUCK = (byte) 0x7d;
	public final static Byte OP_2DROP = (byte) 0x6d;
	public final static Byte OP_2DUP = (byte) 0x6e;
	public final static Byte OP_3DUP = (byte) 0x6f;
	public final static Byte OP_2OVER = (byte) 0x70;
	public final static Byte OP_2ROT = (byte) 0x71;
	public final static Byte OP_2SWAP = (byte) 0x72;
	public final static Byte OP_CAT = (byte) 0x7e;
	public final static Byte OP_SUBSTR = (byte) 0x7f;
	public final static Byte OP_LEFT = (byte) 0x80;
	public final static Byte OP_RIGHT = (byte) 0x81;
	public final static Byte OP_SIZE = (byte) 0x82;
	public final static Byte OP_INVERT = (byte) 0x83;
	public final static Byte OP_AND = (byte) 0x84;
	public final static Byte OP_OR = (byte) 0x85;
	public final static Byte OP_XOR = (byte) 0x86;
	public final static Byte OP_EQUAL = (byte) 0x87;
	public final static Byte OP_EQUALVERIFY = (byte) 0x88;
	public final static Byte OP_1ADD = (byte) 0x8b;
	public final static Byte OP_1SUB = (byte) 0x8c;
	public final static Byte OP_2MUL = (byte) 0x8d;
	public final static Byte OP_2DIV = (byte) 0x8e;
	public final static Byte OP_NEGATE = (byte) 0x8f;
	public final static Byte OP_ABS = (byte) 0x90;
	public final static Byte OP_NOT = (byte) 0x91;
	public final static Byte OP_0NOTEQUAL = (byte) 0x92;
	public final static Byte OP_ADD = (byte) 0x93;
	public final static Byte OP_SUB = (byte) 0x94;
	public final static Byte OP_MUL = (byte) 0x95;
	public final static Byte OP_DIV = (byte) 0x96;
	public final static Byte OP_MOD = (byte) 0x97;
	public final static Byte OP_LSHIFT = (byte) 0x98;
	public final static Byte OP_RSHIFT = (byte) 0x99;
	public final static Byte OP_BOOLAND = (byte) 0x9a;
	public final static Byte OP_BOOLOR = (byte) 0x9b;
	public final static Byte OP_NUMEQUAL = (byte) 0x9c;
	public final static Byte OP_NUMEQUALVERIFY = (byte) 0x9d;
	public final static Byte OP_NUMNOTEQUAL = (byte) 0x9e;
	public final static Byte OP_LESSTHAN = (byte) 0x9f;
	public final static Byte OP_GREATERTHAN = (byte) 0xa0;
	public final static Byte OP_LESSTHANOREQUAL = (byte) 0xa1;
	public final static Byte OP_GREATERTHANOREQUAL = (byte) 0xa2;
	public final static Byte OP_MIN = (byte) 0xa3;
	public final static Byte OP_MAX = (byte) 0xa4;
	public final static Byte OP_WITHIN = (byte) 0xa5;
	public final static Byte OP_RIPEMD160 = (byte) 0xa6;
	public final static Byte OP_SHA1 = (byte) 0xa7;
	public final static Byte OP_SHA256 = (byte) 0xa8;
	public final static Byte OP_HASH160 = (byte) 0xa9;
	public final static Byte OP_HASH256 = (byte) 0xaa;
	public final static Byte OP_CODESEPARATOR = (byte) 0xab;
	public final static Byte OP_CHECKSIG = (byte) 0xac;
	public final static Byte OP_CHECKSIGVERIFY = (byte) 0xad;
	public final static Byte OP_CHECKMULTISIG = (byte) 0xae;
	public final static Byte OP_CHECKMULTISIGVERIFY = (byte) 0xaf;
	public final static Byte OP_PUBKEYHASH = (byte) 0xfd;
	public final static Byte OP_PUBKEY = (byte) 0xfe;
	public final static Byte OP_INVALIDOPCODE = (byte) 0xff;
	public final static Byte OP_VER = (byte) 0x62;
	public final static Byte OP_VERIF = (byte) 0x65;
	public final static Byte OP_VERNOTIF = (byte) 0x66;
	public final static Byte OP_RESERVED1 = (byte) 0x89;
	public final static Byte OP_RESERVED2 = (byte) 0x8a;
	public final static Byte OP_NOP1 = (byte) 0xb0;
	public final static Byte OP_NOP2 = (byte) 0xb1;
	public final static Byte OP_NOP3 = (byte) 0xb2;
	public final static Byte OP_NOP4 = (byte) 0xb3;
	public final static Byte OP_NOP5 = (byte) 0xb4;
	public final static Byte OP_NOP6 = (byte) 0xb5;
	public final static Byte OP_NOP7 = (byte) 0xb6;
	public final static Byte OP_NOP8 = (byte) 0xb7;
	public final static Byte OP_NOP9 = (byte) 0xb8;
	public final static Byte OP_NAME_NEW = (byte) 0x51;
	public final static Byte OP_NAME_FIRSTUPDATE = (byte) 0x52;
	public final static Byte OP_NAME_UPDATE = (byte) 0x53;

	public final static Map<Byte, String> opcodes = new HashMap<>();
	static {
		for (Field f : Script.class.getDeclaredFields()) {
			if (f.getType().equals(Byte.class)) {
				try {
					f.setAccessible(true);
					opcodes.put((Byte) f.get(Script.class), f.getName());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					
				}
			}
		}
	}
	
	public static String scriptToString(byte[] byteScript)  {
		StringBuilder sb = new StringBuilder();
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(byteScript));
		while(true) {
			try{
				byte op = (byte) reader.readUInt8();
				if(op >= 0x01 && op < OP_PUSHDATA1) {
					// Put next [op] bytes on
					byte[] buffer = new byte[op];
					reader.read(buffer);
					sb.append(new String(buffer, "utf-8")).append(" ");
				} else if(op == OP_PUSHDATA1) {
					int length = (int) reader.readUInt8();
					byte[] buffer = new byte[length];
					reader.read(buffer);
					sb.append(opcodes.get(op)).append(" ");
					sb.append(new String(buffer, "utf-8")).append(" ");
				} else if(op == OP_PUSHDATA2) {
					int length = (int) reader.readUInt16LE();
					byte[] buffer = new byte[length];
					reader.read(buffer);
					sb.append(opcodes.get(op)).append(" ");
					sb.append(new String(buffer, "utf-8")).append(" ");
				} else if(op == OP_PUSHDATA4) {
					int length = (int) reader.readUInt32LE();
					byte[] buffer = new byte[length];
					reader.read(buffer);
					sb.append(opcodes.get(op)).append(" ");
					sb.append(new String(buffer, "utf-8")).append(" ");
				} else if(opcodes.containsKey(op)) {
					sb.append(opcodes.get(op)).append(" ");
				}
			} catch(IOException e) {
				return sb.toString();
			}
		}
	}
}
