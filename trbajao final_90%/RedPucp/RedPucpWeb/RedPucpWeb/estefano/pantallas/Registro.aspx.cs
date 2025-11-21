using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Registro : System.Web.UI.Page
    {
        //estado estado1;
        private Usuario_comunWSClient usuario_ComunWS;
        private usuarioComun usuario;
        private estado estado;
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            bool exito = true;
            try
            {
                usuario_ComunWS = new Usuario_comunWSClient();
                usuario = new usuarioComun();
                string nombre = txtNombre.Text.Trim();
                string email = txtEmail.Text.Trim();
                string codigo = txtCodigo.Text.Trim();
                string contrasena = txtContrasena.Text.Trim();
                string descripcion = txtDescripcion.Text.Trim();
                usuario.nombre = nombre;
                usuario.email = email;
                usuario.codigopucp = codigo;
                usuario.contrasenha = contrasena;
                usuario.descripcion = descripcion;
                estado = estado.Nuevo;
                usuario.tipousuario = "C";
                usuario_ComunWS.guardarUsuarioComun(usuario, estado);
                //AdministradorWSClient administradorWSClient = new AdministradorWSClient();
                // aqupi iria la logica con el backend para registrar al usuario

                usuario.idUsuario = usuario_ComunWS.obtenerUsuarioPorCorreo(email).idUsuario;
                //var usuarios = usuario_ComunWS.ListarUsuariosComunes();
                //int id = usuarios[usuarios.Count() - 1].idUsuario;
                //usuario.idUsuario = id;
            }
            catch (System.Exception ex)
            {
                Response.Write("<script>alert('Hubo un error al registrarse. Intente nuevamente.');</script>");
                exito = false;
                return;
            }

            if (exito)
            {
                //guardamos la sesion del usuario
                Session["Usuario"] = usuario.nombre;
                Session["UsuarioCompleto"] = usuario;

                string contenido;
                contenido = "SG9sYSB1c3VhcmlvIG51ZXZvIGRlIGxhIHJlZCBzb2NpYWwgZGUgZXN0dWRpYW50ZXMgcGFyYSBlc3R1ZGlhbnRlcyBkZSBsYSBQVUNQLiBUZSBkYW1vcyBsYSBiaWVudmVuaWRhIGEgZXN0ZSBudWV2byB2aWFqZSBxdWUgZXNwZcOhbW9zIGRpc2ZydXRlcy4gWWEgdmltb3MgcXVlIHR1IG5vbWJyZSB5IGNvcnJlbyBzb24gOiA=";

                string contenido2 = Encoding.UTF8.GetString(Convert.FromBase64String(contenido));
                contenido = contenido2;


                string to = txtEmail.Text.Trim();//correousuario

                if (string.IsNullOrWhiteSpace(to) || !to.Contains("@") || !to.Contains("."))
                {
                    lblError.Text = "Ingrese un correo válido.";
                    return;
                }

                MailAddress addressTo;

                try
                {
                    addressTo = new MailAddress(to);
                }
                catch
                {
                    return;
                }

                string emailSubject = "Registro a RedPUCP";//String emailSubject ="Registro a RedPUCP"


                string emailBody = contenido;

                MailAddress addressFrom = new MailAddress("redpucpinfo@gmail.com", "REDPUCPinfo");
                MailMessage message = new MailMessage(addressFrom, addressTo);
                message.Subject = emailSubject;
                message.IsBodyHtml = true;
                message.Body = emailBody;
                SmtpClient client = new SmtpClient("smtp.gmail.com");
                client.Port = 587;
                client.EnableSsl = true;
                client.UseDefaultCredentials = false;
                client.Credentials =
                new NetworkCredential("redpucpinfo@gmail.com", "hrhy gbau vvyy nyem");

                try
                {
                    client.Send(message);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Trace.TraceInformation("Exception caught in CreateTestMessage2(): {0}",
                        ex.ToString());
                    Console.WriteLine("Exception caught in CreateTestMessage2(): {0}",
                        ex.ToString());
                }

                Response.Redirect("Inicio.aspx");
            }
        }
    }
}