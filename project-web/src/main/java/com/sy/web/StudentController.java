package com.sy.web;

import com.sy.common.enums.StatusCodeEnum;
import com.sy.localhost.entity.Student;
import com.sy.model.entity.ApiResult;
import com.sy.model.page.Pager;
import com.sy.model.request.QueryStudentDataPageRequest;
import com.sy.model.response.QueryStudentDataPageResponse;
import com.sy.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shenyang
 * @Date: 2020/1/10 17:24
 */

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    @RequestMapping("/getAll")
    @ApiOperation(value = "getAll 接口", notes = "requires the Student")
    public Object getAll(@RequestParam(value = "id") String id) {
        Student s = studentService.selectTById(id);
        return s;
    }


    @ApiOperation(value = "queryStudentByKeywordPage 接口", notes = "requires the Student")
    @RequestMapping("/queryStudentByKeywordPage")
    public ApiResult queryStudentByKeywordPage(@RequestBody QueryStudentDataPageRequest request) {
        Pager pager = new Pager();
        BeanUtils.copyProperties(request, pager);
        pager.setSort(StringUtils.isEmpty(request.getSort()) ? "id": request.getSort());
        try {
            pager = studentService.queryStudentDataPage(request.getKeyword(), pager);
            List<QueryStudentDataPageResponse> responseList = new ArrayList<>();
            pager.getList().forEach(one -> {
                QueryStudentDataPageResponse response = new QueryStudentDataPageResponse();
                BeanUtils.copyProperties(one, response);
                responseList.add(response);
            });
            pager.setList(responseList);
            return ApiResult.success(pager);
        } catch (Exception e) {
            log.info("分页查询学生信息 异常", e);
            return ApiResult.error(StatusCodeEnum.OPERATION_FAILURE.getCode(), StatusCodeEnum.CODE_ERROR.getMessage());
        }
    }
}
