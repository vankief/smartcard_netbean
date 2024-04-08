/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacard.connect;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import javacard.define.APPLET;
import javacard.define.RESPONS;
import javacard.utils.ConvertData;
import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ConnectCard {

    public byte[] data;
    public String message;
    public String strID;
    public String strName;
    public String strDate;
    public String strDiaChi;
    public String strPhone;
    public int SoDu;

    private Card card;
    private TerminalFactory factory;
    public CardChannel channel;
    private CardTerminal terminal;
    private List<CardTerminal> terminals;

    private static ConnectCard instance;

    public static ConnectCard getInstance() {
        if (instance == null) {
            instance = new ConnectCard();
        }
        return instance;
    }

    public String connectapplet() {
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x00, APPLET.AID_APPLET));
            String kq = answer.toString();
            data = answer.getData();
            return kq;
        } catch (Exception ex) {
            return "Error";
        }
    }

    public boolean verifyPin(String pin) {
        connectapplet();
        byte[] pinbyte = pin.getBytes();
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_VERIFY_PIN, 0x00, 0x00, pinbyte));
            message = Integer.toHexString(answer.getSW());
            switch (message.toUpperCase()) {
                case RESPONS.SW_NO_ERROR:
                    return true;
                case RESPONS.SW_VERYFI_2:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai. Bạn còn 2 lần thử");
                    return false;
                case RESPONS.SW_VERYFI_1:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai. Bạn còn 1 lần thử");
                    return false;
                case RESPONS.SW_IDENTITY_BLOCKED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai quá số lần thử!Thẻ đã bị khoá");
                    return false;
                case RESPONS.SW_INVALID_PARAMETER:
                    JOptionPane.showMessageDialog(null, "Độ dài pin chưa hợp lệ");
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean createPIN(String pin) {

        byte[] pinbyte = pin.getBytes();
        byte lengt = (byte) pinbyte.length;

        byte[] send = new byte[lengt + 1];
        send[0] = lengt;
        for (int i = 1; i < send.length; i++) {
            send[i] = pinbyte[i - 1];
        }
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_CREATE_PIN, 0x00, 0x03, send));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case RESPONS.SW_NO_ERROR:
                    return true;
                case RESPONS.SW_INVALID_PARAMETER:
                    JOptionPane.showMessageDialog(null, "Lỗi độ dài pin");
                    return false;
                case RESPONS.SW_WRONG_LENGTH:
                    JOptionPane.showMessageDialog(null, "Lỗi SW_WRONG_LENGTH");
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ChangePIN(String oldPin, String newPin) {
        connectapplet();
        byte[] pinOldByte = oldPin.getBytes();
        byte lengtOld = (byte) pinOldByte.length;

        byte[] pinNewByte = newPin.getBytes();
        byte lengtNew = (byte) pinNewByte.length;

        byte[] send = new byte[lengtNew + lengtOld + 2];
        int offSet = 0;
        send[offSet] = lengtOld;
        offSet += 1;
        System.arraycopy(pinOldByte, 0, send, offSet, lengtOld);
        offSet += lengtOld;
        send[offSet] = lengtNew;
        offSet += 1;
        System.arraycopy(pinNewByte, 0, send, offSet, lengtNew);
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_CHANGE_PIN, 0x00, 0x00, send));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case RESPONS.SW_NO_ERROR:
                    JOptionPane.showMessageDialog(null, "Cập nhật PIN thành công!");
                    return true;
                case RESPONS.SW_AUTH_FAILED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai PIN");
                    return false;
                case RESPONS.SW_IDENTITY_BLOCKED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai quá số lần thử!Thẻ đã bị khoá");
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean UnblockPin(byte[] aid) {
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU selectBlockcard = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x00, 0x00, aid));

            String check = Integer.toHexString(selectBlockcard.getSW());

            if (check.equals(RESPONS.SW_NO_ERROR)) {
                CardChannel channel2 = card.getBasicChannel();

                ResponseAPDU unblockCard = channel2.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_UNBLOCK_PIN, 0x00, 0x00));
                message = unblockCard.toString();
                switch (((message.split("="))[1]).toUpperCase()) {
                    case RESPONS.SW_NO_ERROR:
                        JOptionPane.showMessageDialog(null, "Mở khoá thẻ thành công");
                        return true;
                    case RESPONS.SW_OPERATION_NOT_ALLOWED:
                        JOptionPane.showMessageDialog(null, "Thẻ không bị khoá vui lòng kiểm tra lại!");
                        return false;
                    default:
                        return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ResetPin() {
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_RESET_PIN, 0x00, 0x03));
            JOptionPane.showMessageDialog(null, "Đặt lại PIN thành công");
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public void setUp() {

        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_SETUP, 0x00, 0x00));

        } catch (Exception ex) {
            //return "Error";
        }

    }

    public boolean CreateInformation(byte[] data) {
        //connectapplet();
        setUp();
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_CREATE_INFORMATION, 0x00, 0x00, data));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case "9000":
                    return true;
                case RESPONS.SW_WRONG_LENGTH:
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean EditInformation(byte[] data) {
       //connectapplet();
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_CHANGE_INFORMATION, 0x00, 0x00, data));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case "9000":
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
                    return true;
                case RESPONS.SW_WRONG_LENGTH:
                    JOptionPane.showMessageDialog(null, "Dữ liệu quá lớn, vui lòng kiểm tra lại!");
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ReadInformation() {
        connectapplet();
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answerID = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_ID, 0x00));
            strID = new String(answerID.getData());

            ResponseAPDU answerName = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_NAME, 0x00));
            strName = new String(answerName.getData());

            ResponseAPDU answerDate = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_DATE, 0x00));
            strDate = new String(answerDate.getData());

            ResponseAPDU answerDiaChi = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_ADDRESS, 0x00));
            strDiaChi = new String(answerDiaChi.getData());

            ResponseAPDU answerPhone = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_PHONE, 0x00));
            strPhone = new String(answerPhone.getData());

            ResponseAPDU answerSoDu = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_SO_DU, 0x00));
            SoDu = ByteBuffer.wrap(answerSoDu.getData()).getInt();
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean UploadImage(File file, String type) {
        connectapplet();
        try {

            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channel = card.getBasicChannel();

            BufferedImage bImage = ImageIO.read(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, type, bos);

            byte[] napanh = bos.toByteArray();

            int soLan = napanh.length / 249;

            String strsend = soLan + "S" + napanh.length % 249;

            byte[] send = strsend.getBytes();
            for (int i = 0; i < send.length; i++) {
                System.out.printf("0x%02X", send[i]);
            }
            System.out.println("String SEND: " + strsend);

            ResponseAPDU response = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_CREATE_SIZEIMAGE, 0x00, 0x01, send));
            String check = Integer.toHexString(response.getSW());

            if (check.equals(RESPONS.SW_NO_ERROR)) {
                for (int i = 0; i <= soLan; i++) {
                    byte p1 = (byte) i;
                    int start = 0, end = 0;
                    start = i * 249;
                    if (i != soLan) {
                        end = (i + 1) * 249;
                    } else {
                        end = napanh.length;
                    }
                    byte[] slice = Arrays.copyOfRange(napanh, start, end);
                    response = channel.transmit(new CommandAPDU(0xB0, APPLET.INS_CREATE_IMAGE, p1, 0x01, slice));
                    String checkSlide = Integer.toHexString(response.getSW());
                    if (!checkSlide.equals(RESPONS.SW_NO_ERROR)) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        } catch (IOException | CardException ex) {
            return false;
        }
    }

    public BufferedImage DownloadImage() {                                   
        try {
            TerminalFactory factory = TerminalFactory.getDefault();

            List<CardTerminal> terminals = factory.terminals().list();

            CardTerminal terminal = terminals.get(0);

            Card card = terminal.connect("*");

            CardChannel channelImage = card.getBasicChannel();

            int size = 0;
            ResponseAPDU answer = channelImage.transmit(new CommandAPDU(0xB0, APPLET.INS_OUT_SIZEIMAGE, 0x01, 0x01));
            String check = Integer.toHexString(answer.getSW());
            if (check.equals(RESPONS.SW_NO_ERROR)) {
                byte[] sizeAnh = answer.getData();
                if (ConvertData.isByteArrayAllZero(sizeAnh)) {
                    return null;
                }
                byte[] arrAnh = new byte[10000];
                String strSizeAnh = new String(sizeAnh);
                String[] outPut1 = strSizeAnh.split("S");

                int lan = Integer.parseInt(outPut1[0].replaceAll("\\D", ""));
                int du = Integer.parseInt(outPut1[1].replaceAll("\\D", ""));
                size = lan * 249 + du;
                int count = size / 249;
                System.err.println(count);
                for (int j = 0; j <= count; j++) {
                    answer = channelImage.transmit(new CommandAPDU(0xB0, APPLET.INS_OUT_IMAGE, (byte) j, 0x01));
                    String check1 = Integer.toHexString(answer.getSW());
                    if (check1.equals(RESPONS.SW_NO_ERROR)) {
                        byte[] result = answer.getData();
                        int leng = 249;
                        if (j == count) {
                            leng = size % 249;
                        }
                        System.arraycopy(result, 0, arrAnh, j * 249, leng);
                    }
                }

                ByteArrayInputStream bais = new ByteArrayInputStream(arrAnh);
                try {
                    BufferedImage image = ImageIO.read(bais);
                    return image;
                } catch (Exception e) {
                    System.err.println("Error image");
                }
            }
        } catch (Exception e) {
            System.err.println("error dowloadimage");
        }
        return null;
    }

    public String CheckId() {
        //connectapplet();
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answerID = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_OUT_INFORMATION, APPLET.OUT_ID, 0x00));
            strID = new String(answerID.getData());
            return strID;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public boolean TopUp(byte[] data) {
        connectapplet();
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();
            ResponseAPDU answer = channel.transmit(new CommandAPDU(APPLET.CLA, APPLET.INS_DEPOSIT, 0x00, 0x00, data));
            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case "9000":
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
