using RedPucpWeb.RedPucpWS;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RedPucpWeb.utils
{
    public static class Karma
    {
        public enum AccionKarma
        {
            CrearPublicacion = 2, // implementado
            Comentar = 2, // implementado
            PublicacionComentada = 4, // implementado
            ComentarioVotadoUp = 1,
            ComentarioVotadoDown = -1,
            PublicacionVotadaUp = 1, // implementado
            PublicacionVotadoDown = -1, // implementado
            SancionXReporte = -10,
            ReporteFalso = -2,
            Suspension = -10
        }

        // Método opcional para mayor claridad
        public static int ObtenerPuntaje(AccionKarma accion)
        {
            return (int)accion;
        }

        // Método que aplica karma directamente (muy útil)
        public static void AplicarKarma(int usuarioId, AccionKarma accion)
        {
            try
            {
                var wsClient = new Usuario_comunWSClient();
                int puntaje = (int)accion;
                wsClient.actualizarKarma(usuarioId, puntaje);
            }
            catch (System.Exception ex)
            {
                // Log silencioso
                System.Diagnostics.Debug.WriteLine($"Error aplicando karma: {ex.Message}");
            }
        }
    }
}