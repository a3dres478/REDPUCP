using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RedPucpWeb.utils
{
    public static class TipoVoto {
        public enum Valor {
            Up = 'U',
            Down = 'D'
        }

        public static char ObtenerCaracter(Valor tipoVoto)
        {
            return (char)tipoVoto;
        }
    }
    
}