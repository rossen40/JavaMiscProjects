package mainApp.messages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mainApp.ParadoxUtil;

public class ParadoxIPPacket {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ParadoxIPPacket.class);

    public static final byte[] EMPTY_PAYLOAD = new byte[0];

    /**
     * Start of header - always 0xAA
     */
    private byte startOfHeader = (byte) 0xAA;

    /**
     * Payload length - 2 bytes (LL HH)
     */
    private short payloadLength = 0;

    /**
     * "Message Type: 0x01: IP responses 0x02: Serial/pass through cmd response
     * 0x03: IP requests 0x04: Serial/pass through cmd requests"
     */
    private byte messageType = 0x03;

    /**
     * "IP Encryption 0x08: Disabled 0x09: Enabled"
     */
    private byte encryption = 0x08;
    private byte command = 0;
    private byte subCommand = 0;
    private byte unknown0 = 0x0A;

    /**
     * Padding bytes to fill the header to 16 bytes with 0xEE.
     */
    private long theRest = 0xEEEEEEEEEEEEEEEEl;
    private byte[] payload;
    private boolean isChecksumRequired;

    public ParadoxIPPacket(IPPacketPayload payload) throws IOException {
        this(payload.getBytes(), true);
    }


    public ParadoxIPPacket(String payload, boolean isChecksumRequired) throws IOException {
        this(payload.getBytes("US-ASCII"), isChecksumRequired);
    }

    public ParadoxIPPacket(byte[] payload, boolean isChecksumRequired) throws IOException {
        this.isChecksumRequired = isChecksumRequired;

        if (payload == null) {
            this.payload = new byte[0];
            this.payloadLength = 0;
        } else {
            this.payload = payload;
            this.payloadLength = (short) payload.length;
        }

        // TODO: Figure out how to fill up to 16, 32, 48, etc sizes with 0xEE
        // if (payload.length < 16) {
        // this.payload = extendPayload(16, payload);
        // } else {
        // }
    }

    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        outputStream.write(startOfHeader);
        outputStream.write(ByteBuffer.allocate(Short.SIZE / Byte.SIZE).order(ByteOrder.LITTLE_ENDIAN)
            .putShort(payloadLength).array());
        outputStream.write(messageType);
        outputStream.write(encryption);
        outputStream.write(command);
        outputStream.write(subCommand);
        outputStream.write(unknown0);
        outputStream.write(ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(theRest).array());
        outputStream.write(payload);
        byte[] byteArray = outputStream.toByteArray();

        if (isChecksumRequired) {
            byteArray[byteArray.length - 1] = ParadoxUtil.calculateChecksum(payload);
        }

        return byteArray;
    }

    public byte getStartOfHeader() {
        return startOfHeader;
    }

    public ParadoxIPPacket setStartOfHeader(byte startOfHeader) {
        this.startOfHeader = startOfHeader;
        return this;
    }

    public short getPayloadLength() {
        return payloadLength;
    }

    public ParadoxIPPacket setPayloadLength(short payloadLength) {
        this.payloadLength = payloadLength;
        return this;
    }

    public byte getMessageType() {
        return messageType;
    }

    public ParadoxIPPacket setMessageType(byte messageType) {
        this.messageType = messageType;
        return this;
    }

    public byte getEncryption() {
        return encryption;
    }

    public ParadoxIPPacket setEncryption(byte encryption) {
        this.encryption = encryption;
        return this;
    }

    public byte getCommand() {
        return command;
    }

    public ParadoxIPPacket setCommand(byte command) {
        this.command = command;
        return this;
    }

    public byte getSubCommand() {
        return subCommand;
    }

    public ParadoxIPPacket setSubCommand(byte subCommand) {
        this.subCommand = subCommand;
        return this;
    }

    public byte getUnknown0() {
        return unknown0;
    }

    public ParadoxIPPacket setUnknown0(byte unknown0) {
        this.unknown0 = unknown0;
        return this;
    }

    public long getTheRest() {
        return theRest;
    }

    public ParadoxIPPacket setTheRest(long theRest) {
        this.theRest = theRest;
        return this;
    }

}
