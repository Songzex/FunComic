package com.example.funcomicadmin.comic;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class adminFileController {


/*
    private  final OSSService ossService;

    public FileController(OSSService ossService) {
        this.ossService = ossService;
    }
*/

/*
    @PostMapping("/upload")
    public Result<String>uploadFile(@RequestParam("file") MultipartFile file) {
        String url = ossService.uploadFile(file);
           Result<String> result = new Result<String>();
           result.setResult(url);
        return result;
    }
*/

//    @GetMapping("/download/{fileName}")
//    public ResponseResult<String> downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {
//        InputStream inputStream = ossService.downloadFile(fileName);
//        if (inputStream != null) {
//            try {
//                StreamUtils.copy(inputStream, response.getOutputStream());
//                response.setContentType("application/octet-stream");
//                response.flushBuffer();
//            } catch (IOException e) {
//                // 处理下载失败的逻辑
//                ResponseResult.success("文件已经不存了！！");
//            }
//        } else {
//            // 处理文件不存在的逻辑
//        }
//        return ResponseResult.success("下载成功！！");
//    }
}
