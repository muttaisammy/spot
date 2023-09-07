/*
 Highmaps JS v11.1.0 (2023-06-05)

 (c) 2009-2021 Torstein Honsi

 License: www.highcharts.com/license
*/
'use strict';(function(c){"object"===typeof module&&module.exports?(c["default"]=c,module.exports=c):"function"===typeof define&&define.amd?define("highcharts/modules/heatmap",["highcharts"],function(x){c(x);c.Highcharts=x;return c}):c("undefined"!==typeof Highcharts?Highcharts:void 0)})(function(c){function x(c,f,D,t){c.hasOwnProperty(f)||(c[f]=t.apply(null,D),"function"===typeof CustomEvent&&window.dispatchEvent(new CustomEvent("HighchartsModuleLoaded",{detail:{path:f,module:c[f]}})))}c=c?c._modules:
{};x(c,"Core/Axis/Color/ColorAxisComposition.js",[c["Core/Color/Color.js"],c["Core/Utilities.js"]],function(c,f){var v=c.parse,t=f.addEvent,y=f.extend,r=f.merge,p=f.pick,J=f.splat,u;(function(h){function c(){var k=this,d=this.options;this.colorAxis=[];d.colorAxis&&(d.colorAxis=J(d.colorAxis),d.colorAxis.forEach(function(d){new a(k,d)}))}function u(a){var d=this,k=function(k){k=a.allItems.indexOf(k);-1!==k&&(d.destroyItem(a.allItems[k]),a.allItems.splice(k,1))},b=[],e,g;(this.chart.colorAxis||[]).forEach(function(a){(e=
a.options)&&e.showInLegend&&(e.dataClasses&&e.visible?b=b.concat(a.getDataClassLegendSymbols()):e.visible&&b.push(a),a.series.forEach(function(a){if(!a.options.showInLegend||e.dataClasses)"point"===a.options.legendType?a.points.forEach(function(a){k(a)}):k(a)}))});for(g=b.length;g--;)a.allItems.unshift(b[g])}function C(a){a.visible&&a.item.legendColor&&a.item.legendItem.symbol.attr({fill:a.item.legendColor})}function q(){var a=this.chart.colorAxis;a&&a.forEach(function(a,d,b){a.update({},b)})}function w(){(this.chart.colorAxis&&
this.chart.colorAxis.length||this.colorAttribs)&&this.translateColors()}function K(){var a=this.axisTypes;a?-1===a.indexOf("colorAxis")&&a.push("colorAxis"):this.axisTypes=["colorAxis"]}function m(a){var d=this,b=a?"show":"hide";d.visible=d.options.visible=!!a;["graphic","dataLabel"].forEach(function(a){if(d[a])d[a][b]()});this.series.buildKDTree()}function b(){var a=this,d=this.options.nullColor,b=this.colorAxis,e=this.colorKey;(this.data.length?this.data:this.points).forEach(function(k){var n=k.getNestedProperty(e);
(n=k.options.color||(k.isNull||null===k.value?d:b&&"undefined"!==typeof n?b.toColor(n,k):k.color||a.color))&&k.color!==n&&(k.color=n,"point"===a.options.legendType&&k.legendItem&&k.legendItem.label&&a.chart.legend.colorizeItem(k,k.visible))})}function d(d){var b=d.prototype.createAxis;d.prototype.createAxis=function(d,k){if("colorAxis"!==d)return b.apply(this,arguments);var e=new a(this,r(k.axis,{index:this[d].length,isX:!1}));this.isDirtyLegend=!0;this.axes.forEach(function(a){a.series=[]});this.series.forEach(function(a){a.bindAxes();
a.isDirtyData=!0});p(k.redraw,!0)&&this.redraw(k.animation);return e}}function g(){this.elem.attr("fill",v(this.start).tweenTo(v(this.end),this.pos),void 0,!0)}function e(){this.elem.attr("stroke",v(this.start).tweenTo(v(this.end),this.pos),void 0,!0)}var l=[],a;h.compose=function(k,n,h,z,G){a||(a=k);f.pushUnique(l,n)&&(k=n.prototype,k.collectionsWithUpdate.push("colorAxis"),k.collectionsWithInit.colorAxis=[k.addColorAxis],t(n,"afterGetAxes",c),d(n));f.pushUnique(l,h)&&(n=h.prototype,n.fillSetter=
g,n.strokeSetter=e);f.pushUnique(l,z)&&(t(z,"afterGetAllItems",u),t(z,"afterColorizeItem",C),t(z,"afterUpdate",q));f.pushUnique(l,G)&&(y(G.prototype,{optionalAxis:"colorAxis",translateColors:b}),y(G.prototype.pointClass.prototype,{setVisible:m}),t(G,"afterTranslate",w,{order:1}),t(G,"bindAxes",K))};h.pointSetVisible=m})(u||(u={}));return u});x(c,"Core/Axis/Color/ColorAxisDefaults.js",[],function(){return{lineWidth:0,minPadding:0,maxPadding:0,gridLineColor:"#ffffff",gridLineWidth:1,tickPixelInterval:72,
startOnTick:!0,endOnTick:!0,offset:0,marker:{animation:{duration:50},width:.01,color:"#999999"},labels:{distance:8,overflow:"justify",rotation:0},minColor:"#e6e9ff",maxColor:"#0022ff",tickLength:5,showInLegend:!0}});x(c,"Core/Axis/Color/ColorAxis.js",[c["Core/Axis/Axis.js"],c["Core/Color/Color.js"],c["Core/Axis/Color/ColorAxisComposition.js"],c["Core/Axis/Color/ColorAxisDefaults.js"],c["Core/Legend/LegendSymbol.js"],c["Core/Series/SeriesRegistry.js"],c["Core/Utilities.js"]],function(c,f,D,t,y,r,p){var v=
this&&this.__extends||function(){var h=function(m,b){h=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(d,b){d.__proto__=b}||function(d,b){for(var e in b)Object.prototype.hasOwnProperty.call(b,e)&&(d[e]=b[e])};return h(m,b)};return function(m,b){function d(){this.constructor=m}if("function"!==typeof b&&null!==b)throw new TypeError("Class extends value "+String(b)+" is not a constructor or null");h(m,b);m.prototype=null===b?Object.create(b):(d.prototype=b.prototype,new d)}}(),u=f.parse,
h=r.series,z=p.extend,F=p.isArray,C=p.isNumber,q=p.merge,w=p.pick;f=function(c){function m(b,d){var g=c.call(this,b,d)||this;g.beforePadding=!1;g.chart=void 0;g.coll="colorAxis";g.dataClasses=void 0;g.options=void 0;g.stops=void 0;g.visible=!0;g.init(b,d);return g}v(m,c);m.compose=function(b,d,g,e){D.compose(m,b,d,g,e)};m.prototype.init=function(b,d){var g=b.options.legend||{},e=d.layout?"vertical"!==d.layout:"vertical"!==g.layout,l=d.visible;g=q(m.defaultColorAxisOptions,d,{showEmpty:!1,title:null,
visible:g.enabled&&!1!==l});this.side=d.side||e?2:1;this.reversed=d.reversed||!e;this.opposite=!e;c.prototype.init.call(this,b,g,"colorAxis");this.userOptions=d;F(b.userOptions.colorAxis)&&(b.userOptions.colorAxis[this.index]=d);d.dataClasses&&this.initDataClasses(d);this.initStops();this.horiz=e;this.zoomEnabled=!1};m.prototype.initDataClasses=function(b){var d=this.chart,g=this.legendItem=this.legendItem||{},e=b.dataClasses.length,l=this.options,a,k=0,n=d.options.chart.colorCount;this.dataClasses=
a=[];g.labels=[];(b.dataClasses||[]).forEach(function(b,g){b=q(b);a.push(b);if(d.styledMode||!b.color)"category"===l.dataClassColor?(d.styledMode||(g=d.options.colors,n=g.length,b.color=g[k]),b.colorIndex=k,k++,k===n&&(k=0)):b.color=u(l.minColor).tweenTo(u(l.maxColor),2>e?.5:g/(e-1))})};m.prototype.hasData=function(){return!!(this.tickPositions||[]).length};m.prototype.setTickPositions=function(){if(!this.dataClasses)return c.prototype.setTickPositions.call(this)};m.prototype.initStops=function(){this.stops=
this.options.stops||[[0,this.options.minColor],[1,this.options.maxColor]];this.stops.forEach(function(b){b.color=u(b[1])})};m.prototype.setOptions=function(b){c.prototype.setOptions.call(this,b);this.options.crosshair=this.options.marker};m.prototype.setAxisSize=function(){var b=this.legendItem&&this.legendItem.symbol,d=this.chart,g=d.options.legend||{},e,l;b?(this.left=g=b.attr("x"),this.top=e=b.attr("y"),this.width=l=b.attr("width"),this.height=b=b.attr("height"),this.right=d.chartWidth-g-l,this.bottom=
d.chartHeight-e-b,this.len=this.horiz?l:b,this.pos=this.horiz?g:e):this.len=(this.horiz?g.symbolWidth:g.symbolHeight)||m.defaultLegendLength};m.prototype.normalizedValue=function(b){this.logarithmic&&(b=this.logarithmic.log2lin(b));return 1-(this.max-b)/(this.max-this.min||1)};m.prototype.toColor=function(b,d){var g=this.dataClasses,e=this.stops,l;if(g)for(l=g.length;l--;){var a=g[l];var k=a.from;e=a.to;if(("undefined"===typeof k||b>=k)&&("undefined"===typeof e||b<=e)){var n=a.color;d&&(d.dataClass=
l,d.colorIndex=a.colorIndex);break}}else{b=this.normalizedValue(b);for(l=e.length;l--&&!(b>e[l][0]););k=e[l]||e[l+1];e=e[l+1]||k;b=1-(e[0]-b)/(e[0]-k[0]||1);n=k.color.tweenTo(e.color,b)}return n};m.prototype.getOffset=function(){var b=this.legendItem&&this.legendItem.group,d=this.chart.axisOffset[this.side];if(b){this.axisParent=b;c.prototype.getOffset.call(this);var g=this.chart.legend;g.allItems.forEach(function(b){b instanceof m&&b.drawLegendSymbol(g,b)});g.render();this.chart.getMargins(!0);this.added||
(this.added=!0,this.labelLeft=0,this.labelRight=this.width);this.chart.axisOffset[this.side]=d}};m.prototype.setLegendColor=function(){var b=this.reversed,d=b?1:0;b=b?0:1;d=this.horiz?[d,0,b,0]:[0,b,0,d];this.legendColor={linearGradient:{x1:d[0],y1:d[1],x2:d[2],y2:d[3]},stops:this.stops}};m.prototype.drawLegendSymbol=function(b,d){var g;d=d.legendItem||{};var e=b.padding,l=b.options,a=this.options.labels,k=w(l.itemDistance,10),n=this.horiz,c=w(l.symbolWidth,n?m.defaultLegendLength:12),h=w(l.symbolHeight,
n?12:m.defaultLegendLength),G=w(l.labelPadding,n?16:30);this.setLegendColor();d.symbol||(d.symbol=this.chart.renderer.symbol("roundedRect",0,b.baseline-11,c,h,{r:null!==(g=l.symbolRadius)&&void 0!==g?g:3}).attr({zIndex:1}).add(d.group));d.labelWidth=c+e+(n?k:w(a.x,a.distance)+this.maxLabelLength);d.labelHeight=h+e+(n?G:0)};m.prototype.setState=function(b){this.series.forEach(function(d){d.setState(b)})};m.prototype.setVisible=function(){};m.prototype.getSeriesExtremes=function(){var b=this.series,
d=b.length,g;this.dataMin=Infinity;for(this.dataMax=-Infinity;d--;){var e=b[d];var l=e.colorKey=w(e.options.colorKey,e.colorKey,e.pointValKey,e.zoneAxis,"y");var a=e.pointArrayMap;var k=e[l+"Min"]&&e[l+"Max"];if(e[l+"Data"])var n=e[l+"Data"];else if(a){n=[];a=a.indexOf(l);var c=e.yData;if(0<=a&&c)for(g=0;g<c.length;g++)n.push(w(c[g][a],c[g]))}else n=e.yData;k?(e.minColorValue=e[l+"Min"],e.maxColorValue=e[l+"Max"]):(n=h.prototype.getExtremes.call(e,n),e.minColorValue=n.dataMin,e.maxColorValue=n.dataMax);
"undefined"!==typeof e.minColorValue&&(this.dataMin=Math.min(this.dataMin,e.minColorValue),this.dataMax=Math.max(this.dataMax,e.maxColorValue));k||h.prototype.applyExtremes.call(e)}};m.prototype.drawCrosshair=function(b,d){var g=this.legendItem||{},e=d&&d.plotX,l=d&&d.plotY,a=this.pos,k=this.len;if(d){var n=this.toPixels(d.getNestedProperty(d.series.colorKey));n<a?n=a-2:n>a+k&&(n=a+k+2);d.plotX=n;d.plotY=this.len-n;c.prototype.drawCrosshair.call(this,b,d);d.plotX=e;d.plotY=l;this.cross&&!this.cross.addedToColorAxis&&
g.group&&(this.cross.addClass("highcharts-coloraxis-marker").add(g.group),this.cross.addedToColorAxis=!0,this.chart.styledMode||"object"!==typeof this.crosshair||this.cross.attr({fill:this.crosshair.color}))}};m.prototype.getPlotLinePath=function(b){var d=this.left,g=b.translatedValue,e=this.top;return C(g)?this.horiz?[["M",g-4,e-6],["L",g+4,e-6],["L",g,e],["Z"]]:[["M",d,g],["L",d-6,g+6],["L",d-6,g-6],["Z"]]:c.prototype.getPlotLinePath.call(this,b)};m.prototype.update=function(b,d){var g=this.chart.legend;
this.series.forEach(function(b){b.isDirtyData=!0});(b.dataClasses&&g.allItems||this.dataClasses)&&this.destroyItems();c.prototype.update.call(this,b,d);this.legendItem&&this.legendItem.label&&(this.setLegendColor(),g.colorizeItem(this,!0))};m.prototype.destroyItems=function(){var b=this.chart,d=this.legendItem||{};if(d.label)b.legend.destroyItem(this);else if(d.labels){var g=0;for(d=d.labels;g<d.length;g++)b.legend.destroyItem(d[g])}b.isDirtyLegend=!0};m.prototype.destroy=function(){this.chart.isDirtyLegend=
!0;this.destroyItems();c.prototype.destroy.apply(this,[].slice.call(arguments))};m.prototype.remove=function(b){this.destroyItems();c.prototype.remove.call(this,b)};m.prototype.getDataClassLegendSymbols=function(){var b=this,d=b.chart,g=b.legendItem&&b.legendItem.labels||[],e=d.options.legend,l=w(e.valueDecimals,-1),a=w(e.valueSuffix,""),k=function(a){return b.series.reduce(function(b,d){b.push.apply(b,d.points.filter(function(b){return b.dataClass===a}));return b},[])},n;g.length||b.dataClasses.forEach(function(e,
c){var h=e.from,m=e.to,f=d.numberFormatter,u=!0;n="";"undefined"===typeof h?n="< ":"undefined"===typeof m&&(n="> ");"undefined"!==typeof h&&(n+=f(h,l)+a);"undefined"!==typeof h&&"undefined"!==typeof m&&(n+=" - ");"undefined"!==typeof m&&(n+=f(m,l)+a);g.push(z({chart:d,name:n,options:{},drawLegendSymbol:y.rectangle,visible:!0,isDataClass:!0,setState:function(a){for(var b=0,d=k(c);b<d.length;b++)d[b].setState(a)},setVisible:function(){this.visible=u=b.visible=!u;for(var a=0,e=k(c);a<e.length;a++)e[a].setVisible(u);
d.legend.colorizeItem(this,u)}},e))});return g};m.defaultColorAxisOptions=t;m.defaultLegendLength=200;m.keepProps=["legendItem"];return m}(c);Array.prototype.push.apply(c.keepProps,f.keepProps);"";return f});x(c,"Series/ColorMapComposition.js",[c["Core/Series/SeriesRegistry.js"],c["Core/Utilities.js"]],function(c,f){var v=c.seriesTypes.column.prototype,t=f.addEvent,y=f.defined,r;(function(c){function p(c){this.moveToTopOnHover&&this.graphic&&this.graphic.attr({zIndex:c&&"hover"===c.state?1:0})}var u=
[];c.pointMembers={dataLabelOnNull:!0,moveToTopOnHover:!0,isValid:function(){return null!==this.value&&Infinity!==this.value&&-Infinity!==this.value&&(void 0===this.value||!isNaN(this.value))}};c.seriesMembers={colorKey:"value",axisTypes:["xAxis","yAxis","colorAxis"],parallelArrays:["x","y","value"],pointArrayMap:["value"],trackerGroups:["group","markerGroup","dataLabelsGroup"],colorAttribs:function(c){var h={};!y(c.color)||c.state&&"normal"!==c.state||(h[this.colorProp||"fill"]=c.color);return h},
pointAttribs:v.pointAttribs};c.compose=function(c){var h=c.prototype.pointClass;f.pushUnique(u,h)&&t(h,"afterSetState",p);return c}})(r||(r={}));return r});x(c,"Series/Heatmap/HeatmapPoint.js",[c["Core/Series/SeriesRegistry.js"],c["Core/Utilities.js"]],function(c,f){var v=this&&this.__extends||function(){var c=function(f,h){c=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(c,h){c.__proto__=h}||function(c,h){for(var f in h)Object.prototype.hasOwnProperty.call(h,f)&&(c[f]=h[f])};return c(f,
h)};return function(f,h){function z(){this.constructor=f}if("function"!==typeof h&&null!==h)throw new TypeError("Class extends value "+String(h)+" is not a constructor or null");c(f,h);f.prototype=null===h?Object.create(h):(z.prototype=h.prototype,new z)}}(),t=f.clamp,y=f.defined,r=f.extend,p=f.pick;c=function(c){function f(){var h=null!==c&&c.apply(this,arguments)||this;h.options=void 0;h.series=void 0;h.value=void 0;h.x=void 0;h.y=void 0;return h}v(f,c);f.prototype.applyOptions=function(h,f){(this.isNull||
null===this.value)&&delete this.color;c.prototype.applyOptions.call(this,h,f);this.formatPrefix=this.isNull||null===this.value?"null":"point";return this};f.prototype.getCellAttributes=function(){var c=this.series,f=c.options,u=(f.colsize||1)/2,v=(f.rowsize||1)/2,q=c.xAxis,w=c.yAxis,r=this.options.marker||c.options.marker;c=c.pointPlacementToXValue();var m=p(this.pointPadding,f.pointPadding,0),b={x1:t(Math.round(q.len-q.translate(this.x-u,!1,!0,!1,!0,-c)),-q.len,2*q.len),x2:t(Math.round(q.len-q.translate(this.x+
u,!1,!0,!1,!0,-c)),-q.len,2*q.len),y1:t(Math.round(w.translate(this.y-v,!1,!0,!1,!0)),-w.len,2*w.len),y2:t(Math.round(w.translate(this.y+v,!1,!0,!1,!0)),-w.len,2*w.len)};[["width","x"],["height","y"]].forEach(function(d){var c=d[0];d=d[1];var e=d+"1",l=d+"2",a=Math.abs(b[e]-b[l]),k=r&&r.lineWidth||0,n=Math.abs(b[e]+b[l])/2;c=r&&r[c];y(c)&&c<a&&(c=c/2+k/2,b[e]=n-c,b[l]=n+c);if(m){if("x"===d&&q.reversed||"y"===d&&!w.reversed)e=l,l=d+"1";b[e]+=m;b[l]-=m}});return b};f.prototype.haloPath=function(c){if(!c)return[];
var f=this.shapeArgs||{},h=f.x;h=void 0===h?0:h;var p=f.y;p=void 0===p?0:p;var q=f.width;q=void 0===q?0:q;f=f.height;f=void 0===f?0:f;return[["M",h-c,p-c],["L",h-c,p+f+c],["L",h+q+c,p+f+c],["L",h+q+c,p-c],["Z"]]};f.prototype.isValid=function(){return Infinity!==this.value&&-Infinity!==this.value};return f}(c.seriesTypes.scatter.prototype.pointClass);r(c.prototype,{dataLabelOnNull:!0,moveToTopOnHover:!0,ttBelow:!1});return c});x(c,"Series/Heatmap/HeatmapSeries.js",[c["Core/Color/Color.js"],c["Series/ColorMapComposition.js"],
c["Core/Globals.js"],c["Series/Heatmap/HeatmapPoint.js"],c["Core/Series/SeriesRegistry.js"],c["Core/Renderer/SVG/SVGRenderer.js"],c["Core/Utilities.js"]],function(c,f,x,t,y,r,p){var v=this&&this.__extends||function(){var b=function(c,a){b=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(a,b){a.__proto__=b}||function(a,b){for(var c in b)Object.prototype.hasOwnProperty.call(b,c)&&(a[c]=b[c])};return b(c,a)};return function(c,a){function d(){this.constructor=c}if("function"!==typeof a&&
null!==a)throw new TypeError("Class extends value "+String(a)+" is not a constructor or null");b(c,a);c.prototype=null===a?Object.create(a):(d.prototype=a.prototype,new d)}}(),u=this&&this.__assign||function(){u=Object.assign||function(b){for(var c,a=1,d=arguments.length;a<d;a++){c=arguments[a];for(var e in c)Object.prototype.hasOwnProperty.call(c,e)&&(b[e]=c[e])}return b};return u.apply(this,arguments)},h=x.doc,z=y.series,F=y.seriesTypes;x=F.column;var C=F.scatter,q=r.prototype.symbols,w=p.clamp;
r=p.extend;var D=p.fireEvent,m=p.isNumber,b=p.merge,d=p.pick,g=p.defined;p=function(e){function f(){var a=null!==e&&e.apply(this,arguments)||this;a.canvas=void 0;a.colorAxis=void 0;a.context=void 0;a.data=void 0;a.options=void 0;a.points=void 0;a.valueMax=NaN;a.valueMin=NaN;return a}v(f,e);f.prototype.drawPoints=function(){var a=this,b=a.options,c=b.marker||{};if(b.interpolation){c=a.image;var e=a.chart,f=a.xAxis,h=a.yAxis,m=a.points,l=m.length-1,p=f.len,q=f.reversed,v=h.len,t=h.reversed,r=f.getExtremes(),
x=r.min,y=r.max;h=h.getExtremes();var C=h.min,D=h.max;b=[d(b.colsize,1),d(b.rowsize,1)];h=b[0];r=b[1];var H=e.inverted,A=h/2;b=f.userOptions.minPadding;var B=g(b)&&!(0<b);b=H||B;A=B&&A||0;A=[x-A,y+2*A,x+h].map(function(b){return w(Math.round(f.len-f.translate(b,!1,!0,!1,!0,-a.pointPlacementToXValue())),-f.len,2*f.len)});B=A[0];var E=A[1];E=q?[E,B]:[B,E];B=E[0];E=E[1];A=p/A[2]/2/2/2;p=H?{width:p,height:v,x:0,y:0}:{x:B-A,width:E-A,height:v,y:0};if(!c||a.isDirtyData){var F=e.colorAxis&&e.colorAxis[0];
v=a.getContext();if((H=a.canvas)&&v&&F){var I=H.width=~~((y-x)/h)+1;h=H.height=~~((D-C)/r)+1;r=I*h;A=new Uint8ClampedArray(4*r);var L=I-(b&&1||0),M=h-1;b=function(a){a=F.toColor(a.value||0,d(a)).split(")")[0].split("(")[1].split(",").map(function(a){return d(parseFloat(a),parseInt(a,10))});a[3]=255*d(a[3],1);return a};var J=q?function(a){return L-a}:function(a){return a},K=t?function(a){return M-a}:function(a){return a};q=function(a,b){return Math.ceil(I*K(~~((M-0)/(D-C)*(D-b-C)))+J(~~((L-0)/(y-x)*
(a-x))))};a.buildKDTree();a.directTouch=!1;for(t=0;t<r;t++)B=m[~~((l-0)/(A.length-4)*t*4)],E=new Uint8ClampedArray(b(B)),A.set(E,4*q(B.x,B.y));v.putImageData(new ImageData(A,I,h),0,0);c?c.attr(u(u({},p),{href:H.toDataURL()})):a.image=e.renderer.image(H.toDataURL()).attr(p).add(a.group)}}else c.width===p.width&&c.height===p.height||c.attr(p)}else if(c.enabled||a._hasPointMarkers)z.prototype.drawPoints.call(a),a.points.forEach(function(b){b.graphic&&(b.graphic[a.chart.styledMode?"css":"animate"](a.colorAttribs(b)),
null===b.value&&b.graphic.addClass("highcharts-null-point"))})};f.prototype.getContext=function(){var a=this.canvas,b=this.context;if(a&&b)b.clearRect(0,0,a.width,a.height);else return this.canvas=h.createElement("canvas"),this.context=this.canvas.getContext("2d")||void 0;return b};f.prototype.getExtremes=function(){var a=z.prototype.getExtremes.call(this,this.valueData),b=a.dataMin;a=a.dataMax;m(b)&&(this.valueMin=b);m(a)&&(this.valueMax=a);return z.prototype.getExtremes.call(this)};f.prototype.getValidPoints=
function(a,b){return z.prototype.getValidPoints.call(this,a,b,!0)};f.prototype.hasData=function(){return!!this.processedXData.length};f.prototype.init=function(){e.prototype.init.apply(this,arguments);var a=this.options;a.pointRange=d(a.pointRange,a.colsize||1);this.yAxis.axisPointRange=a.rowsize||1;q.ellipse=q.circle;a.marker&&m(a.borderRadius)&&(a.marker.r=a.borderRadius)};f.prototype.markerAttribs=function(a,b){var c=a.shapeArgs||{};if(a.hasImage)return{x:a.plotX,y:a.plotY};if(b&&"normal"!==b){var d=
a.options.marker||{};a=this.options.marker||{};a=a.states&&a.states[b]||{};d=d.states&&d.states[b]||{};b=(d.width||a.width||c.width||0)+(d.widthPlus||a.widthPlus||0);a=(d.height||a.height||c.height||0)+(d.heightPlus||a.heightPlus||0);return{x:(c.x||0)+((c.width||0)-b)/2,y:(c.y||0)+((c.height||0)-a)/2,width:b,height:a}}return c};f.prototype.pointAttribs=function(a,d){var e=z.prototype.pointAttribs.call(this,a,d),f=this.options||{},g=this.chart.options.plotOptions||{},h=g.series||{},k=g.heatmap||{};
g=a&&a.options.borderColor||f.borderColor||k.borderColor||h.borderColor;h=a&&a.options.borderWidth||f.borderWidth||k.borderWidth||h.borderWidth||e["stroke-width"];e.stroke=a&&a.marker&&a.marker.lineColor||f.marker&&f.marker.lineColor||g||this.color;e["stroke-width"]=h;d&&"normal"!==d&&(a=b(f.states&&f.states[d],f.marker&&f.marker.states&&f.marker.states[d],a&&a.options.states&&a.options.states[d]||{}),e.fill=a.color||c.parse(e.fill).brighten(a.brightness||0).get(),e.stroke=a.lineColor||e.stroke);
return e};f.prototype.translate=function(){var a=this.options,c=a.borderRadius,d=(a=a.marker)&&a.symbol||"rect",e=q[d]?d:"rect",f=-1!==["circle","square"].indexOf(e);this.generatePoints();this.points.forEach(function(a){var g=a.getCellAttributes(),h=Math.min(g.x1,g.x2),k=Math.min(g.y1,g.y2),l=Math.max(Math.abs(g.x2-g.x1),0),n=Math.max(Math.abs(g.y2-g.y1),0);a.hasImage=0===(a.marker&&a.marker.symbol||d||"").indexOf("url");f&&(k=Math.abs(l-n),h=Math.min(g.x1,g.x2)+(l<n?0:k/2),k=Math.min(g.y1,g.y2)+
(l<n?k/2:0),l=n=Math.min(l,n));a.hasImage&&(a.marker={width:l,height:n});a.plotX=a.clientX=(g.x1+g.x2)/2;a.plotY=(g.y1+g.y2)/2;a.shapeType="path";a.shapeArgs=b(!0,{x:h,y:k,width:l,height:n},{d:q[e](h,k,l,n,{r:m(c)?c:0})})});D(this,"afterTranslate")};f.defaultOptions=b(C.defaultOptions,{animation:!1,borderRadius:0,borderWidth:0,interpolation:!1,nullColor:"#f7f7f7",dataLabels:{formatter:function(){var a=this.series.chart.numberFormatter,b=this.point.value;return m(b)?a(b,-1):""},inside:!0,verticalAlign:"middle",
crop:!1,overflow:"allow",padding:0},marker:{symbol:"rect",radius:0,lineColor:void 0,states:{hover:{lineWidthPlus:0},select:{}}},clip:!0,pointRange:null,tooltip:{pointFormat:"{point.x}, {point.y}: {point.value}<br/>"},states:{hover:{halo:!1,brightness:.2}},legendSymbol:"rectangle"});return f}(C);r(p.prototype,{axisTypes:f.seriesMembers.axisTypes,colorKey:f.seriesMembers.colorKey,directTouch:!0,getExtremesFromAll:!0,parallelArrays:f.seriesMembers.parallelArrays,pointArrayMap:["y","value"],pointClass:t,
specialGroup:"group",trackerGroups:f.seriesMembers.trackerGroups,alignDataLabel:x.prototype.alignDataLabel,colorAttribs:f.seriesMembers.colorAttribs,getSymbol:z.prototype.getSymbol});f.compose(p);y.registerSeriesType("heatmap",p);"";"";return p});x(c,"masters/modules/heatmap.src.js",[c["Core/Globals.js"],c["Core/Axis/Color/ColorAxis.js"]],function(c,f){c.ColorAxis=f;f.compose(c.Chart,c.Fx,c.Legend,c.Series)})});
//# sourceMappingURL=heatmap.js.map