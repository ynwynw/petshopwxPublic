package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.ShangpinleixingEntity;
import com.cl.entity.view.ShangpinleixingView;

import com.cl.service.ShangpinleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 商品类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
@RestController
@RequestMapping("/shangpinleixing")
public class ShangpinleixingController {
    @Autowired
    private ShangpinleixingService shangpinleixingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangpinleixingEntity shangpinleixing,
		HttpServletRequest request){
        EntityWrapper<ShangpinleixingEntity> ew = new EntityWrapper<ShangpinleixingEntity>();

		PageUtils page = shangpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinleixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShangpinleixingEntity shangpinleixing, 
		HttpServletRequest request){
        EntityWrapper<ShangpinleixingEntity> ew = new EntityWrapper<ShangpinleixingEntity>();

		PageUtils page = shangpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShangpinleixingEntity shangpinleixing){
       	EntityWrapper<ShangpinleixingEntity> ew = new EntityWrapper<ShangpinleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shangpinleixing, "shangpinleixing")); 
        return R.ok().put("data", shangpinleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShangpinleixingEntity shangpinleixing){
        EntityWrapper< ShangpinleixingEntity> ew = new EntityWrapper< ShangpinleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shangpinleixing, "shangpinleixing")); 
		ShangpinleixingView shangpinleixingView =  shangpinleixingService.selectView(ew);
		return R.ok("查询商品类型成功").put("data", shangpinleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShangpinleixingEntity shangpinleixing = shangpinleixingService.selectById(id);
		shangpinleixing = shangpinleixingService.selectView(new EntityWrapper<ShangpinleixingEntity>().eq("id", id));
        return R.ok().put("data", shangpinleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShangpinleixingEntity shangpinleixing = shangpinleixingService.selectById(id);
		shangpinleixing = shangpinleixingService.selectView(new EntityWrapper<ShangpinleixingEntity>().eq("id", id));
        return R.ok().put("data", shangpinleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增商品类型")
    public R save(@RequestBody ShangpinleixingEntity shangpinleixing, HttpServletRequest request){
        if(shangpinleixingService.selectCount(new EntityWrapper<ShangpinleixingEntity>().eq("leixing", shangpinleixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
    	shangpinleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangpinleixing);
        shangpinleixingService.insert(shangpinleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增商品类型")
    @RequestMapping("/add")
    public R add(@RequestBody ShangpinleixingEntity shangpinleixing, HttpServletRequest request){
        if(shangpinleixingService.selectCount(new EntityWrapper<ShangpinleixingEntity>().eq("leixing", shangpinleixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
    	shangpinleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangpinleixing);
        shangpinleixingService.insert(shangpinleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改商品类型")
    public R update(@RequestBody ShangpinleixingEntity shangpinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shangpinleixing);
        shangpinleixingService.updateById(shangpinleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除商品类型")
    public R delete(@RequestBody Long[] ids){
        shangpinleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
