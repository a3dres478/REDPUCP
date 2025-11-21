using CloudinaryDotNet;
using CloudinaryDotNet.Actions;
using System;
using System.Configuration;
using System.Security.Principal;
using System.Web;
namespace Prueba_Frontend.Servicios
{
    public class CloudinaryImageService
    {
        private readonly Cloudinary _cloudinary;
        public CloudinaryImageService()
        {
            var account = new Account(
            ConfigurationManager.AppSettings["cloudinary_cloud_name"],
           ConfigurationManager.AppSettings["cloudinary_api_key"],
           ConfigurationManager.AppSettings["cloudinary_api_secret"]
           );
            _cloudinary = new Cloudinary(account);
        }
        public string UploadImage(HttpPostedFile file)
        {
            try
            {
                // 1. Validar
                if (file == null || file.ContentLength == 0)
                    return "ERROR: No se seleccionó ningún archivo";
                if (file.ContentLength > 10 * 1024 * 1024) // 10MB
                    return "ERROR: El archivo es demasiado grande. Máximo  10MB" ;


            string fileName = file.FileName.ToLower();
                if (!fileName.EndsWith(".jpg") &&
                !fileName.EndsWith(".jpeg") &&
                !fileName.EndsWith(".png") &&
                !fileName.EndsWith(".webp"))
                {
                    return "ERROR: Formato no soportado. Use JPG, PNG o WebP";
                }
                // 2. Preparar la subida y el archivo
                var uploadParams = new ImageUploadParams()
                {
                    File = new FileDescription(file.FileName,
                file.InputStream),
                    PublicId = $"redpucp/{Guid.NewGuid()}",
                    Folder = "redpucp_imagenes"
                };
                var uploadResult = _cloudinary.Upload(uploadParams);
                if (uploadResult.Error != null)
                    return $"ERROR: {uploadResult.Error.Message}";
                // 3. Obtener la url de la imagen
                return uploadResult.SecureUrl.ToString();
            }
            catch (Exception ex)
            {
                return $"ERROR: {ex.Message}";
            }
        }
    }
}