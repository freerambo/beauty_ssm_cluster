package com.github.cruiser.web;

import com.github.cruiser.entity.AbstractEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


/**
 * 定义常用资源的路径
 */
public interface IController<T extends AbstractEntity> {

    /**
     * 分页显示实体资源
     * 示例：
     * <pre>
     * @RequestMapping(value = "PATH/resources?limit={limit}&offset={offset}", method = RequestMethod.GET,
     *     produces = {MediaType.APPLICATION_JSON_VALUE})
     *               </pre>
     *
     * @param limit  每页大小
     * @param offset 每页开始
     * @return
     */
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getByLimit(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset);

    /*
     * 根据属性筛选实体资源
     * 示例：
     * <pre>
     *     public ResponseEntiry getBySelectiveParam(
     * @RequestParam(value="param1", required=false) String param1,
     * @RequestParam(value="param2", required = false) String param2)
     * </pre>
     *
     * @param params 筛选参数，按照实际标明
     * @return
     */
    //public ResponseEntity<List<T>> getBySelective(Map params);

    /**
     * 通过id获取某一实体资源
     * <pre>@RequestMapping(value = "PATH/resources/{id}", method = RequestMethod.GET,
     * produces = {MediaType.APPLICATION_JSON_VALUE})
     * </pre>
     *
     * @param id 实体id号
     *           示例：
     * @return
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getEntityById(@PathVariable("id") long id);


    /**
     * 新增一个实体资源
     * 示例：
     * <pre>@RequestMapping(value = "PATH/resources/", method = RequestMethod.POST)</pre>
     *
     * @param entity
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody T entity,
                                             UriComponentsBuilder ucBuilder);

    /**
     * 更新一个实体资源
     * 示例：
     * <pre>@RequestMapping(value = "PATH/resources/{id}", method = RequestMethod.PUT)
     * public ResponseEntity<User> updateEntity(@PathVariable("id") long id, @RequestBody User user)</pre>
     *
     * @param id        实体id号
     * @param entity    实体
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> updateEntity(@PathVariable("id") long id,
                                          @RequestBody T entity,
                                          UriComponentsBuilder ucBuilder);

    /**
     * 根据提交的值部分更新一个实体资源
     * 示例：
     * <pre>@RequestMapping(value = "PATH/resources/{id}", method = RequestMethod.PATCH)
     * public ResponseEntity<User> updateEntity(@PathVariable("id") long id, @RequestBody User user)</pre>
     *
     * @param id        实体id号
     * @param entity    实体
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> updateEntityBySelective(@PathVariable("id") long id,
                                                     @RequestBody T entity,
                                                     UriComponentsBuilder ucBuilder);


    /**
     * 删除一个实体资源
     * 示例：
     * <pre>@RequestMapping(value = "PATH/resources/{id}", method = RequestMethod.DELETE)</pre>
     *
     * @param id 实体id号
     * @return
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<T> deleteEntity(@PathVariable("id") long id);

}