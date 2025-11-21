/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;

/**
 *
 * @author HECTOR
 */
import com.cloudinary.Cloudinary;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CloudinaryService {
    private Cloudinary cloudinary;
    private final String cloud_name;
    private final String api_key;
    private final String api_secret;
    
    public CloudinaryService() {
        this.cloud_name = "dh9fjm2ph";
        this.api_key = "675249252644744";  
        this.api_secret = "wMhfqfH3XpzTth22EqnEe-hZnIc";
    }
    
    private void initializeCloudinary() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", this.cloud_name);    
            config.put("api_key", this.api_key);          
            config.put("api_secret", this.api_secret);   
            cloudinary = new Cloudinary(config);
        }
    }
    
    public String uploadImage(byte[] fileBytes, String fileName) {
        try {
            initializeCloudinary();
            
            Map<String, Object> options = new HashMap<>();
            options.put("public_id", "red_pucp/" + System.currentTimeMillis() + "_" + fileName);
            options.put("folder", "red_pucp");
            
            Map uploadResult = cloudinary.uploader().upload(fileBytes, options);
            return (String) uploadResult.get("secure_url");
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}