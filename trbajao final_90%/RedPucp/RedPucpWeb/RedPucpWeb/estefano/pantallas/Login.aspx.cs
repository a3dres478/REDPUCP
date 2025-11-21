using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Login : System.Web.UI.Page
    {
        Usuario_comunWSClient Usuario_comunWS;
        protected void Page_Load(object sender, EventArgs e)
        {
            // Si ya está logueado, redirigir al inicio
            if (Session["UsuarioLogueado"] != null)
            {
                Response.Redirect("Inicio.aspx");
            }

            // Configurar validadores
            if (!IsPostBack)
            {
                ConfigurarValidadores();

            }
            ValidationSettings.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;

        }

        protected private void ConfigurarValidadores()
        {
            // Configurar validación de email
            var revEmail = new RegularExpressionValidator
            {
                ID = "revEmail",
                ControlToValidate = "txtEmail",
                ValidationExpression = @"^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$",
                ErrorMessage = "Formato de email inválido.",
                CssClass = "text-danger",
                Display = ValidatorDisplay.Dynamic
            };
            form1.Controls.Add(revEmail);
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            Page.Validate();

            if (Page.IsValid)
            {
                string email = txtEmail.Text.Trim();
                string password = txtPassword.Text;

                try
                {
                    if (ValidarCredenciales(email, password))
                    {
                        // Login exitoso
                        Session["UsuarioLogueado"] = true;

                        usuario usuarioActual = Session["UsuarioCompleto"] as usuario;
                        administrador adminActual = Session["AdminCompleto"] as administrador;

                        if (usuarioActual == null && adminActual != null)
                        {
                            Response.Redirect("Dashboard.aspx");
                        } else
                        {
                            // Redirigir al inicio
                            Response.Redirect("Inicio.aspx");
                        }
                    }
                    else
                    {
                        MostrarError("Credenciales incorrectas. Verifica tu email y contraseña.");
                    }
                }
                catch (System.Exception ex)
                {
                    MostrarError($"Error al iniciar sesión: {ex.Message}");
                }
            }
        }

        private bool ValidarCredenciales(string email, string password)
        {
            try
            {
                using (var usuarioClient = new Usuario_comunWSClient())
                {
                    bool login_exitoso = usuarioClient.verificarLoginUsuarioComun(email,password);
                    if (login_exitoso) {
                        var usuario = usuarioClient.obtenerUsuarioPorCorreo(email);
                        if (usuario.tipousuario.Equals("C"))
                        {
                            Session["Usuario"] = usuario.nombre;
                            Session["UsuarioCompleto"] = usuario;
                        }
                        else {
                            var admin = new AdministradorWSClient().obtenerAdminPorCorreo(email);
                            Session["Admin"] = admin.nombre;
                            Session["AdminCompleto"] = admin;
                        }
                        return true;
                    }


                    //var usuarios = usuarioClient.ListarUsuariosComunes();

                    //if (usuarios != null)
                    //{
                    //    var usuario = usuarios.FirstOrDefault(u =>
                    //        u.email.Equals(email, StringComparison.OrdinalIgnoreCase) &&
                    //        u.contrasenha == password);

                    //    if (usuario != null)
                    //    {
                    //        Session["Usuario"] = usuario.nombre;
                    //        Session["UsuarioCompleto"] = usuario;
                    //        return usuario != null;
                    //    }
                    //    else
                    //    {
                    //        var adminClient = new AdministradorWSClient();
                    //        var admins = adminClient.listarAdministradores();

                    //        if (admins != null)
                    //        {
                    //            var admin = admins.FirstOrDefault(a =>
                    //                a.email.Equals(email, StringComparison.OrdinalIgnoreCase) &&
                    //                a.clave_acceso == password);

                    //            if (admin != null)
                    //            {
                    //                Session["Admin"] = admin.nombre;
                    //                Session["AdminCompleto"] = admin;
                    //                return admin != null;
                    //            }
                    //        }
                    //    }
                    //}
                }
            }
            catch (System.Exception ex)
            {
                throw new System.Exception($"Error al validar credenciales: {ex.Message}");
            }

            return false;
        }

        private void MostrarError(string mensaje)
        {
            lblError.Text = mensaje;
            lblError.Visible = true;

            ScriptManager.RegisterStartupScript(this, GetType(), "hideError",
                "setTimeout(function() { document.getElementById('" + lblError.ClientID + "').style.display = 'none'; }, 5000);", true);
        }

        // Método para limpiar campos
        private void LimpiarCampos()
        {
            txtEmail.Text = "";
            txtPassword.Text = "";
            lblError.Visible = false;
        }
    }
}