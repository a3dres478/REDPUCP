using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.masterPage
{
    public partial class MasterPageAdmin : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Admin"] == null)
            {
                Response.Redirect("~/Login.aspx");
            }
            else
            {
                lblAdmin.Text = Session["Admin"].ToString();
            }
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("~/Login.aspx");
        }
    }
}