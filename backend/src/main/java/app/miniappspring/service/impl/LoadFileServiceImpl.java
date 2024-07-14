//package app.miniappspring.service.impl;
//
//import app.miniappspring.service.LoadFileService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.random.RandomGenerator;
//
//@Service
//public class LoadFileServiceImpl implements LoadFileService {
//    @Override
//    public ImageUser loadImage(MultipartFile multipartFile) throws IOException {
//        if (multipartFile != null) {
//            ImageUser image;
////            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, multipartFile.getOriginalFilename());
//            try {
//                Long id = RandomGenerator.getDefault().nextLong();
//                image = ImageUser.builder()
//                        .id(id)
//                        .name(multipartFile.getName())
//                        .originalFileName(multipartFile.getOriginalFilename())
//                        .size(multipartFile.getSize())
//                        .contentType(multipartFile.getContentType())
//                        .bytes(multipartFile.getBytes())
//                        .build();
//
////                Files.write(fileNameAndPath, multipartFile.getBytes());
//            } catch (Exception e) {
//                throw e;
//            }
//
//            return image;
//        }
//        return null;
//    }
//}
