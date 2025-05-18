package app.miniappspring.controller.ai;

import app.miniappspring.controller.ai.utils.ReferenceDocsLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/ai/file/upload")
@RequiredArgsConstructor
public class FileUploadAiController {

    private final ReferenceDocsLoader referenceDocsLoader;

    @PostMapping("/documents")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("stFile") MultipartFile stFile
    ) {
        if (!"application/pdf".equalsIgnoreCase(pdfFile.getContentType())) {
            return ResponseEntity.badRequest().body("Неверный формат PDF файла.");
        }

        if (!stFile.getOriginalFilename().endsWith(".st")) {
            return ResponseEntity.badRequest().body("Неверный формат ST файла.");
        }

        Path filePath = Path.of("backend/src/main/resources/prompts/device.st");

        try {
            Files.write(filePath, stFile.getBytes());

            referenceDocsLoader.save(pdfFile);

            System.out.println("PDF загружен: " + pdfFile.getOriginalFilename());
            System.out.println("ST загружен: " + stFile.getOriginalFilename());

            return ResponseEntity.ok("Файлы успешно загружены.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при чтении файлов.");
        }
    }
}
