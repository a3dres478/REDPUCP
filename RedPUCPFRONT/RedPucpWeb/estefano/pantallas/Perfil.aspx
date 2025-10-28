<%@ Page Title="" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPage.master" AutoEventWireup="true" CodeBehind="Perfil.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Perfil" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        .perfil-wrapper {
            max-width: 900px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
            padding: 40px;
        }

        .perfil-header {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-bottom: 30px;
        }

        .perfil-header h2 {
            font-size: 1.8rem;
            margin: 0;
            font-weight: 600;
        }

        .perfil-header .correo {
            font-size: 1.3rem;
            font-weight: bold;
            margin: 5px 0;
        }

        .karma {
            background-color: #d9fdd3;
            color: #2b7a0b;
            border-radius: 8px;
            padding: 4px 12px;
            font-weight: 600;
            font-size: 0.9rem;
            margin-top: 8px;
        }

        .perfil-body label {
            font-weight: bold;
            color: #333;
        }

        .descripcion {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            margin: 8px 0 15px 0;
        }

        .btn-editar {
            background-color: #222;
            color: white;
            width: 100%;
            border: none;
            border-radius: 6px;
            padding: 12px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .btn-editar:hover {
            background-color: #000;
        }

        .publicaciones {
            margin-top: 40px;
        }

        .publicaciones h3 {
            margin-bottom: 20px;
            font-size: 1.4rem;
        }

        .cards-container {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }

        .card {
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.08);
            padding: 15px;
            width: 250px;
            flex-shrink: 0;
        }

        .card h4 {
            font-size: 1rem;
            margin: 0 0 8px;
        }

        .card p {
            font-size: 0.9rem;
            color: #555;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="perfil-wrapper">
        <!-- información general de la cuenta -->
        <div class="perfil-header">
            <h2><asp:Label ID="lblUsuario" runat="server" Text="Usuario" /> - <asp:Label ID="lblCodigo" runat="server" Text="00000000" /></h2>
            <div class="karma"><asp:Label ID="lblKarma" runat="server" Text="2019 Karma" /></div>
            <div class="correo"><asp:Label ID="lblCorreo" runat="server" Text="usuario@pucp.edu.pe" /></div>
        </div>

        <!-- Descripción -->
        <div class="perfil-body">
            <label>Descripción</label>
            <asp:TextBox ID="txtDescripcion" runat="server" CssClass="descripcion" TextMode="MultiLine"
                Rows="3" placeholder="Describe algo sobre ti..."></asp:TextBox>
            <asp:Button ID="btnEditarPerfil" runat="server" Text="Editar Perfil" CssClass="btn-editar" OnClick="btnEditarPerfil_Click" />
        </div>

        <!-- publicacoines más recientes -->
        <div class="publicaciones">
            <h3>Últimas publicaciones</h3>
            <div class="cards-container">
                <div class="card">
                    <h4>¿Cómo puedo realizar…?</h4>
                    <p><strong>r/librosPUCP</strong></p>
                    <p>u/AlessandroM<br />Creo que primero tenías que ir a...</p>
                </div>
                <div class="card">
                    <h4>Solución del ex2 pasa…</h4>
                    <p><strong>r/fa3</strong></p>
                    <p>u/JuanP<br />Gracias, me sirvió de mucho para...</p>
                </div>
                <div class="card">
                    <h4>¿TP es difícil?</h4>
                    <p><strong>r/tecnicasDeProgramacion</strong></p>
                    <p>u/KarenU<br />Siendo completamente sincera creo que...</p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
