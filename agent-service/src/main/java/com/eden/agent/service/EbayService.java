package com.eden.agent.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.OrderIDArrayType;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.TradingRoleCodeType;

/**
 * Create by zhaoxianghui on 2017/11/21.
 */
public class EbayService {

    // eBay Authentication Token
    private static String token = "AgAAAA**AQAAAA**aAAAAA**nz8UWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GnAJSBowSdj6x9nY+seQ**gGMEAA**AAMAAA**8BSgl4oGWWXz0O6Z8pvyApdbTJShEc6LkIzf6Eyi/AKqUKsoAn3atAh6LF42N/mmoG2yq1m0ncpeWEHzKGRnmqQSre5kh0fRWOAUEVYZBhIVO2iLAtPvvgFFskB4t2mupJ2uJua0givEvfML7Ikf3ZMAesX0zzzhsr7uHdHb+lWa+iQPMB1TPz50rhy0lbn8e079LYWsC2Bal9awbv5apsEYQMxKj3dXkEQQKrdW2DAEntYX9CcKc0fum3Gcbjw4Vc2y8KxaOT0rDr1u9UimIfwznEz9SDkkfBf+nhc7uzgxDtwGLtmX7KRS7LGriZn7KPOm5yjneT6qQ1LHp9jOJ/eZrV0pQMh/3iX26UWy6DfaZ8CqXzqb440839M0L+aEB6nuABIOQEUeAWgYAFefx3zo5PU+MuxDBpu43039PWfDt1OKhGbWeAkMuQgYh4lErzCyDgLcIOnHiPoOrpiCYBNICnvL0tRFXXiMUHI3KGru3HFIU74ApjVkmLVuj1z9T6YESvyJAKEDHPgBeNBtXbX5XHqyRT2x0qo8meKSk+8GbzFS8qTlu8Qy44w9qfSiqFa8bekiQn43kTZ/XI1G16DXfhU8oO+5ZSZ9t/82ltfGlrR153OhdTO6a0jnZieQCtQAGTawwck/9a5g3mRaM4p7Qr571oMRM/+Zta5KNVsUOwX4YV+IVUWlcXfIZUhxJbU3F/wFUqSMDeAwJwCr8Ptdltvg80g0dbVqAk0Y8WsoGdJ6aS2rufn7I8r9Pdzu";
    // eBay SOAP server URL (e.g., https://api.sandbox.ebay.com/wsapi):
    private static String serverUrl = "https://api.sandbox.ebay.com/wsapi";

    private static ApiContext apiContext = new ApiContext();

    static {
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(token);
        apiContext.setApiServerUrl(serverUrl);
    }

    public static void main(String[] args) {
        try {
            new EbayService().getOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getOrders() throws Exception {
        String ids = "0,1";
        StringTokenizer st = new StringTokenizer(ids, ",");
        ArrayList lstOrders = new ArrayList();
        while (st.hasMoreTokens()) {
            lstOrders.add(st.nextToken());
        }

        int size = lstOrders.size();
        String[] orderIds = new String[size];
        for (int i = 0; i < size; i++) {
            orderIds[i] = lstOrders.get(i).toString().trim();
        }

        DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
                DetailLevelCodeType.RETURN_ALL,
                DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
                DetailLevelCodeType.ITEM_RETURN_DESCRIPTION
        };

        GetOrdersCall api = new GetOrdersCall(this.apiContext);
        api.setDetailLevel(detailLevels);

        OrderIDArrayType oiat = new OrderIDArrayType();
        oiat.setOrderID(orderIds);
        api.setOrderIDArray(oiat);
        OrderStatusCodeType status = OrderStatusCodeType.ALL;
        api.setOrderStatus(status);

        api.setOrderRole(TradingRoleCodeType.SELLER);
        api.setCreateTimeFrom(string2Calendar("20161010"));

        api.setCreateTimeTo(string2Calendar("20171010"));

        OrderType[] orders = api.getOrders();
    }

    private Calendar string2Calendar(String time) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date =sdf.parse(time);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
