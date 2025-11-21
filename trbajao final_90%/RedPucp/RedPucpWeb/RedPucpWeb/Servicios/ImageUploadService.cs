using Prueba_Frontend.Servicios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RedPucpWeb.Servicios
{
    public class ImageUploadService
    {
        // Constantes según el requisito RF008
        private const long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB en bytes
        private static readonly List<string> ALLOWED_FORMATS = new List<string>
        {
            "image/jpeg",
            "image/png",
            "image/webp"
        };

        public ImageUploadResult UploadImage(HttpPostedFile file)
        {
            var result = new ImageUploadResult();

            try
            {
                // 1. Validar que hay archivo
                if (file == null || file.ContentLength == 0)
                {
                    result.Success = false;
                    result.ErrorMessage = "No se ha seleccionado ningún archivo.";
                    return result;
                }

                // 2. Validar tamaño (RF008 - 10 MB máximo)
                if (file.ContentLength > MAX_FILE_SIZE)
                {
                    result.Success = false;
                    result.ErrorMessage = $"El archivo excede el límite de 10 MB. Tamaño actual: {FormatFileSize(file.ContentLength)}";
                    result.ErrorCode = 413; // Código de error según RF008
                    return result;
                }

                // 3. Validar formato (RF008 - JPG, PNG, WebP)
                if (!ALLOWED_FORMATS.Contains(file.ContentType.ToLower()))
                {
                    result.Success = false;
                    result.ErrorMessage = $"Formato de archivo no permitido. Formatos aceptados: JPG, PNG, WebP. Formato recibido: {file.ContentType}";
                    return result;
                }

                // 4. Validar extensión por seguridad
                string fileExtension = System.IO.Path.GetExtension(file.FileName).ToLower();
                if (!IsValidExtension(fileExtension))
                {
                    result.Success = false;
                    result.ErrorMessage = "Extensión de archivo no permitida.";
                    return result;
                }

                // 5. Subir a Cloudinary
                var cloudinaryService = new CloudinaryImageService();
                string imageUrl = cloudinaryService.UploadImage(file);

                // 6. Validar respuesta de Cloudinary
                if (string.IsNullOrEmpty(imageUrl))
                {
                    result.Success = false;
                    result.ErrorMessage = "Error al subir la imagen al servidor.";
                    return result;
                }

                if (imageUrl.StartsWith("ERROR:", StringComparison.OrdinalIgnoreCase))
                {
                    result.Success = false;
                    result.ErrorMessage = imageUrl.Replace("ERROR:", "").Trim();
                    return result;
                }

                // 7. Éxito
                result.Success = true;
                result.ImageUrl = imageUrl;
                result.FileSize = file.ContentLength;
                result.FileType = file.ContentType;

                return result;
            }
            catch (Exception ex)
            {
                result.Success = false;
                result.ErrorMessage = $"Error inesperado al procesar la imagen: {ex.Message}";
                return result;
            }
        }

        private bool IsValidExtension(string extension)
        {
            var allowedExtensions = new List<string> { ".jpg", ".jpeg", ".png", ".webp" };
            return allowedExtensions.Contains(extension);
        }

        private string FormatFileSize(long bytes)
        {
            string[] sizes = { "B", "KB", "MB", "GB" };
            int order = 0;
            double len = bytes;
            while (len >= 1024 && order < sizes.Length - 1)
            {
                order++;
                len = len / 1024;
            }
            return $"{len:0.##} {sizes[order]}";
        }
    }

    // Clase para el resultado de la subida
    public class ImageUploadResult
    {
        public bool Success { get; set; }
        public string ImageUrl { get; set; }
        public string ErrorMessage { get; set; }
        public int ErrorCode { get; set; }
        public long FileSize { get; set; }
        public string FileType { get; set; }
    }
}