package com.purpleprint.network.purpleprintproject.auth.command.application.service;

import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * <pre>
 * Class : MailService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String email;

    private final UserRepository userRepository;

    @Autowired
    public MailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private  JavaMailSender mailSender;

    @Async
    public void sendEmail(String toEmail, int certCode) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(email);
        helper.setTo(toEmail);

        helper.setSubject("[필로칼로] 회원가입 인증 코드");
        helper.setText("text/html","<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "            <tr>\n" +
                "              <td style=\"padding:40px 30px 30px 30px;text-align:center;font-size:24px;font-weight:bold;\">\n" +
                "                <a href=\"https://ibb.co/YQfBKss\"><img src=\"\" alt=\"\" border=\"0\"></a>\n" +
                "            </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;background-color:#ffffff;\">\n" +
                "                <!--title-->\n" +
                "                <h1 align=\"center\" style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;\">필로칼로을 이용해 주셔서 감사합니다.</h1>\n" +
                "                <!--first paragraph-->\n" +
                "                <p style=\"margin:0;\"></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                "\n" +
                "                <!-- <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div> -->\n" +
                "\n" +
                "                <div  class=\"col-lge\" style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                  <!--second paragraph-->             \n" +
                "                    <p style=\"color:#707070; font-size: 12px; margin-top:0;margin-bottom:12px;\">\n" +
                "                      이메일 인증번호를 입력하시면 인증절차가<br>\n" +
                "                      완료되어 가입을 진행하실 수 있습니다.\n<br>\n" +
                "                      인증시간이 만료되었을 경우,\n\n<br>\n" +
                "                      인증번호 재발송을 진행해 주시기 바랍니다.\n<br>\n" +
                "                    </p>\n" +
                "                 <!--third paragraph-->\n" +
                "                    <p style=\"margin-top:0;margin-bottom:18px;\"></p>\n" +
                "                  <p style=\"margin:0;\"><div href=\"https://example.com/\" style=\"background: #00AEEF; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;text-underline-color:#ff3884\"><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span style=\"mso-text-raise:10pt;font-weight:bold;\">"+certCode+"</span><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></div></p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">Copyright 2022. leisurethat all rights reserved.<br></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>");
        mailSender.send(message);

        System.out.println("메일 발송 완료");

    }

}
