package com.pradeep.currencyconverter.core.common

object CurrencyFlags {

     private const val FLAG_CDN = "https://flagcdn.com/w80"

     private val map = mapOf(
          "AED" to "$FLAG_CDN/ae.png",
          "AFN" to "$FLAG_CDN/af.png",
          "ALL" to "$FLAG_CDN/al.png",
          "AMD" to "$FLAG_CDN/am.png",
          "ANG" to "$FLAG_CDN/cw.png",
          "AOA" to "$FLAG_CDN/ao.png",
          "ARS" to "$FLAG_CDN/ar.png",
          "AUD" to "$FLAG_CDN/au.png",
          "AWG" to "$FLAG_CDN/aw.png",
          "AZN" to "$FLAG_CDN/az.png",

          "BAM" to "$FLAG_CDN/ba.png",
          "BBD" to "$FLAG_CDN/bb.png",
          "BDT" to "$FLAG_CDN/bd.png",
          "BGN" to "$FLAG_CDN/bg.png",
          "BHD" to "$FLAG_CDN/bh.png",
          "BIF" to "$FLAG_CDN/bi.png",
          "BMD" to "$FLAG_CDN/bm.png",
          "BND" to "$FLAG_CDN/bn.png",
          "BOB" to "$FLAG_CDN/bo.png",
          "BRL" to "$FLAG_CDN/br.png",
          "BSD" to "$FLAG_CDN/bs.png",
          "BTN" to "$FLAG_CDN/bt.png",
          "BWP" to "$FLAG_CDN/bw.png",
          "BYN" to "$FLAG_CDN/by.png",
          "BZD" to "$FLAG_CDN/bz.png",

          "CAD" to "$FLAG_CDN/ca.png",
          "CDF" to "$FLAG_CDN/cd.png",
          "CHF" to "$FLAG_CDN/ch.png",
          "CLP" to "$FLAG_CDN/cl.png",
          "CNY" to "$FLAG_CDN/cn.png",
          "COP" to "$FLAG_CDN/co.png",
          "CRC" to "$FLAG_CDN/cr.png",
          "CUP" to "$FLAG_CDN/cu.png",
          "CVE" to "$FLAG_CDN/cv.png",
          "CZK" to "$FLAG_CDN/cz.png",

          "DJF" to "$FLAG_CDN/dj.png",
          "DKK" to "$FLAG_CDN/dk.png",
          "DOP" to "$FLAG_CDN/do.png",
          "DZD" to "$FLAG_CDN/dz.png",

          "EGP" to "$FLAG_CDN/eg.png",
          "ERN" to "$FLAG_CDN/er.png",
          "ETB" to "$FLAG_CDN/et.png",

          "EUR" to "$FLAG_CDN/eu.png",

          "FJD" to "$FLAG_CDN/fj.png",

          "GBP" to "$FLAG_CDN/gb.png",
          "GEL" to "$FLAG_CDN/ge.png",
          "GHS" to "$FLAG_CDN/gh.png",
          "GIP" to "$FLAG_CDN/gi.png",
          "GMD" to "$FLAG_CDN/gm.png",
          "GNF" to "$FLAG_CDN/gn.png",
          "GTQ" to "$FLAG_CDN/gt.png",
          "GYD" to "$FLAG_CDN/gy.png",

          "HKD" to "$FLAG_CDN/hk.png",
          "HNL" to "$FLAG_CDN/hn.png",
          "HTG" to "$FLAG_CDN/ht.png",
          "HUF" to "$FLAG_CDN/hu.png",

          "IDR" to "$FLAG_CDN/id.png",
          "ILS" to "$FLAG_CDN/il.png",
          "INR" to "$FLAG_CDN/in.png",
          "IQD" to "$FLAG_CDN/iq.png",
          "IRR" to "$FLAG_CDN/ir.png",
          "ISK" to "$FLAG_CDN/is.png",

          "JMD" to "$FLAG_CDN/jm.png",
          "JOD" to "$FLAG_CDN/jo.png",
          "JPY" to "$FLAG_CDN/jp.png",

          "KES" to "$FLAG_CDN/ke.png",
          "KGS" to "$FLAG_CDN/kg.png",
          "KHR" to "$FLAG_CDN/kh.png",
          "KMF" to "$FLAG_CDN/km.png",
          "KRW" to "$FLAG_CDN/kr.png",
          "KWD" to "$FLAG_CDN/kw.png",
          "KYD" to "$FLAG_CDN/ky.png",
          "KZT" to "$FLAG_CDN/kz.png",

          "LAK" to "$FLAG_CDN/la.png",
          "LBP" to "$FLAG_CDN/lb.png",
          "LKR" to "$FLAG_CDN/lk.png",
          "LRD" to "$FLAG_CDN/lr.png",
          "LSL" to "$FLAG_CDN/ls.png",
          "LYD" to "$FLAG_CDN/ly.png",

          "MAD" to "$FLAG_CDN/ma.png",
          "MDL" to "$FLAG_CDN/md.png",
          "MGA" to "$FLAG_CDN/mg.png",
          "MKD" to "$FLAG_CDN/mk.png",
          "MMK" to "$FLAG_CDN/mm.png",
          "MNT" to "$FLAG_CDN/mn.png",
          "MOP" to "$FLAG_CDN/mo.png",
          "MRU" to "$FLAG_CDN/mr.png",
          "MUR" to "$FLAG_CDN/mu.png",
          "MVR" to "$FLAG_CDN/mv.png",
          "MWK" to "$FLAG_CDN/mw.png",
          "MXN" to "$FLAG_CDN/mx.png",
          "MYR" to "$FLAG_CDN/my.png",
          "MZN" to "$FLAG_CDN/mz.png",

          "NAD" to "$FLAG_CDN/na.png",
          "NGN" to "$FLAG_CDN/ng.png",
          "NIO" to "$FLAG_CDN/ni.png",
          "NOK" to "$FLAG_CDN/no.png",
          "NPR" to "$FLAG_CDN/np.png",
          "NZD" to "$FLAG_CDN/nz.png",

          "OMR" to "$FLAG_CDN/om.png",

          "PAB" to "$FLAG_CDN/pa.png",
          "PEN" to "$FLAG_CDN/pe.png",
          "PGK" to "$FLAG_CDN/pg.png",
          "PHP" to "$FLAG_CDN/ph.png",
          "PKR" to "$FLAG_CDN/pk.png",
          "PLN" to "$FLAG_CDN/pl.png",
          "PYG" to "$FLAG_CDN/py.png",

          "QAR" to "$FLAG_CDN/qa.png",

          "RON" to "$FLAG_CDN/ro.png",
          "RSD" to "$FLAG_CDN/rs.png",
          "RUB" to "$FLAG_CDN/ru.png",
          "RWF" to "$FLAG_CDN/rw.png",

          "SAR" to "$FLAG_CDN/sa.png",
          "SBD" to "$FLAG_CDN/sb.png",
          "SCR" to "$FLAG_CDN/sc.png",
          "SDG" to "$FLAG_CDN/sd.png",
          "SEK" to "$FLAG_CDN/se.png",
          "SGD" to "$FLAG_CDN/sg.png",
          "SLE" to "$FLAG_CDN/sl.png",
          "SOS" to "$FLAG_CDN/so.png",
          "SRD" to "$FLAG_CDN/sr.png",
          "SSP" to "$FLAG_CDN/ss.png",
          "STN" to "$FLAG_CDN/st.png",
          "SYP" to "$FLAG_CDN/sy.png",
          "SZL" to "$FLAG_CDN/sz.png",

          "THB" to "$FLAG_CDN/th.png",
          "TJS" to "$FLAG_CDN/tj.png",
          "TMT" to "$FLAG_CDN/tm.png",
          "TND" to "$FLAG_CDN/tn.png",
          "TOP" to "$FLAG_CDN/to.png",
          "TRY" to "$FLAG_CDN/tr.png",
          "TTD" to "$FLAG_CDN/tt.png",
          "TWD" to "$FLAG_CDN/tw.png",
          "TZS" to "$FLAG_CDN/tz.png",

          "UAH" to "$FLAG_CDN/ua.png",
          "UGX" to "$FLAG_CDN/ug.png",
          "USD" to "$FLAG_CDN/us.png",
          "UYU" to "$FLAG_CDN/uy.png",
          "UZS" to "$FLAG_CDN/uz.png",

          "VES" to "$FLAG_CDN/ve.png",
          "VND" to "$FLAG_CDN/vn.png",

          "WST" to "$FLAG_CDN/ws.png",

          "XAF" to "$FLAG_CDN/cm.png",
          "XCD" to "$FLAG_CDN/ag.png",
          "XOF" to "$FLAG_CDN/sn.png",
          "XPF" to "$FLAG_CDN/pf.png",

          "YER" to "$FLAG_CDN/ye.png",

          "ZAR" to "$FLAG_CDN/za.png",
          "ZMW" to "$FLAG_CDN/zm.png",
          "ZWL" to "$FLAG_CDN/zw.png"
     )

     fun getFlagUrl(currencyCode: String): String =
          map[currencyCode.uppercase()]?: ""
}