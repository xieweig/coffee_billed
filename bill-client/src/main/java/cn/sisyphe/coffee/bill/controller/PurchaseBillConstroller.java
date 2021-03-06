package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.PurchaseBillManager;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/bill/purchase")
@RestController
@Api(description = "进货入库单相关接口")
@CrossOrigin(origins = "*")
public class PurchaseBillConstroller {
    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 保存进货单
     *
     * @param purchaseBill
     * @return
     */
    @ApiOperation(value = "保存进货单")
    @RequestMapping(path = "/savePurchaseBill", method = RequestMethod.POST)
    public ResponseResult savePurchaseBill(@RequestBody PurchaseBill purchaseBill) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.saveBill(purchaseBill);
        return responseResult;
    }

    /**
     * 提交进货单
     *
     * @param purchaseBill
     * @return
     */
    @ApiOperation(value = "提交进货单")
    @RequestMapping(path = "/submitPurchaseBill", method = RequestMethod.POST)
    public ResponseResult submitPurchaseBill(@RequestBody PurchaseBill purchaseBill) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.submitBill(purchaseBill);
        return responseResult;
    }

    /**
     * 多条件查询进货单
     *
     * @param conditionQueryPurchaseBill
     * @return
     */
    @ApiOperation(value = "多条件查询进货单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        ResponseResult responseResult = new ResponseResult();
        Page<PurchaseBill> billPage = purchaseBillManager.findByConditions(conditionQueryPurchaseBill);
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * 根据进货单编码查询进货单详细信息
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "根据进货单编码查询进货单详细信息")
    @RequestMapping(path = "/findByPurchaseBillCode", method = RequestMethod.GET)
    public ResponseResult findByPurchaseBillCode(@RequestParam String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        PurchaseBill purchaseBill = purchaseBillManager.auditingBill(purchaseBillCode);
        responseResult.put("purchaseBill", purchaseBill);
        return responseResult;
    }

    /**
     * 修改进货单据信息
     *
     * @param purchaseBill
     * @return
     */
    @ApiOperation(value = "修改进货单据信息")
    @RequestMapping(path = "/updatePurchaseBill", method = RequestMethod.POST)
    public ResponseResult updatePurchaseBill(@RequestBody PurchaseBill purchaseBill) {
        ResponseResult responseResult = new ResponseResult();
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.auditFailureBill(purchaseBillCode);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.AuditSuccessBill(purchaseBillCode);
        return responseResult;
    }
}
