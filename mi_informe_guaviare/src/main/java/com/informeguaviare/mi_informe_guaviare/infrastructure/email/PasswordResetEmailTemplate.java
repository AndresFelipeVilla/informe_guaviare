package com.informeguaviare.mi_informe_guaviare.infrastructure.email;

import org.springframework.stereotype.Component;

@Component
public class PasswordResetEmailTemplate {

    public String createResetPasswordEmail(String userName, String resetLink) {
        return HTML_TEMPLATE
                .replace("{{userName}}", userName)
                .replace("{{resetLink}}", resetLink);
    }

    private static final String HTML_TEMPLATE = """
            <!DOCTYPE html>
            <html lang="es" xmlns="http://www.w3.org/1999/xhtml" xmlns:o="urn:schemas-microsoft-com:office:office">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta name="x-apple-disable-message-reformatting">
                <title>Recuperación de Contraseña</title>
                <style>
                    table, td, div, h1, p { font-family: 'Helvetica Neue', Arial, sans-serif; }
                    table, td { border-collapse: collapse !important; mso-table-lspace: 0pt; mso-table-rspace: 0pt; }
                    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; }
                </style>
            </head>
            <body style="margin: 0; padding: 0; background-color: #f4f4f4;">
                <div style="display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;">
                    Solicitud de restablecimiento de contraseña para tu cuenta en Informe Guaviare.
                </div>
                <center style="width: 100%; background-color: #f4f4f4;">
                    <div style="max-width: 600px; margin: 0 auto;">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="width: 100%; max-width: 600px; background-color: #ffffff; margin-top: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); overflow: hidden;">
                            <tr>
                                <td align="center" style="padding: 40px 0 30px 0; background-color: #ffffff; border-bottom: 3px solid #4CAF50;">
                                    <img src="cid:logoImage" alt="Informe Guaviare" width="180" style="display: block; border: 0; outline: none; text-decoration: none; max-width: 200px;" />
                                </td>
                            </tr>
                            <tr>
                                <td style="padding: 40px 30px;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="width: 100%;">
                                        <tr>
                                            <td style="color: #333333; font-size: 24px; font-weight: bold; text-align: center; padding-bottom: 20px;">
                                                Recuperación de Contraseña
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="color: #555555; font-size: 16px; line-height: 24px; text-align: left; padding-bottom: 20px;">
                                                Hola, <strong>{{userName}}</strong>:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="color: #555555; font-size: 16px; line-height: 24px; text-align: left; padding-bottom: 30px;">
                                                Hemos recibido una solicitud para restablecer tu contraseña en <strong>Informe Guaviare</strong>. Haz clic en el botón para continuar:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" style="padding-bottom: 30px;">
                                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="margin: auto;">
                                                    <tr>
                                                        <td align="center" bgcolor="#4CAF50" style="border-radius: 4px;">
                                                            <a href="{{resetLink}}" target="_blank" style="font-size: 16px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; padding: 12px 30px; border-radius: 4px; border: 1px solid #4CAF50; display: inline-block; font-weight: bold; background-color: #4CAF50;">
                                                                Restablecer Contraseña
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="color: #666666; font-size: 14px; line-height: 20px; padding-bottom: 10px;">
                                                O copia este enlace en tu navegador:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="padding-bottom: 30px; word-break: break-all;">
                                                <a href="{{resetLink}}" style="color: #4CAF50; font-size: 13px; text-decoration: underline; line-height: 1.4;">
                                                    {{resetLink}}
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="background-color: #fff8e1; border-left: 4px solid #ffc107; padding: 15px; border-radius: 4px;">
                                                <p style="margin: 0; color: #856404; font-size: 14px; line-height: 20px;">
                                                    <strong>⚠️ Importante:</strong> Este enlace expirará en <strong>60 minutos</strong>.
                                                </p>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="background-color: #eeeeee; padding: 20px; text-align: center; border-top: 1px solid #dddddd;">
                                    <p style="margin: 0 0 10px 0; color: #777777; font-size: 12px;">
                                        © 2025 Informe Guaviare. Todos los derechos reservados.
                                    </p>
                                </td>
                            </tr>
                        </table>
                        <div style="height: 40px; line-height: 40px; font-size: 1px;">&nbsp;</div>
                    </div>
                </center>
            </body>
            </html>
                """;
}