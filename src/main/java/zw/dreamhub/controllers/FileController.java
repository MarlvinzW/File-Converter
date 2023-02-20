package zw.dreamhub.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.dreamhub.domain.dto.request.FileUploaderRequest;
import zw.dreamhub.services.FileService;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/2/2023
 */

@RestController
@RequestMapping(path = "api/v1/files/")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "excelToCsv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> convertCsvToExcel(@Valid @ModelAttribute FileUploaderRequest request) {
        return fileService.convertCsvToExcel(request);
    }
}
