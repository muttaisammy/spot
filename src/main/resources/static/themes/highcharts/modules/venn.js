/*
 Highcharts JS v11.1.0 (2023-06-05)

 (c) 2017-2021 Highsoft AS
 Authors: Jon Arild Nygard

 License: www.highcharts.com/license
*/
'use strict';(function(a){"object"===typeof module&&module.exports?(a["default"]=a,module.exports=a):"function"===typeof define&&define.amd?define("highcharts/modules/venn",["highcharts"],function(l){a(l);a.Highcharts=l;return a}):a("undefined"!==typeof Highcharts?Highcharts:void 0)})(function(a){function l(a,h,g,z){a.hasOwnProperty(h)||(a[h]=z.apply(null,g),"function"===typeof CustomEvent&&window.dispatchEvent(new CustomEvent("HighchartsModuleLoaded",{detail:{path:h,module:a[h]}})))}a=a?a._modules:
{};l(a,"Core/Geometry/GeometryUtilities.js",[],function(){var a;(function(a){a.getCenterOfPoints=function(a){const g=a.reduce((a,g)=>{a.x+=g.x;a.y+=g.y;return a},{x:0,y:0});return{x:g.x/a.length,y:g.y/a.length}};a.getDistanceBetweenPoints=function(a,h){return Math.sqrt(Math.pow(h.x-a.x,2)+Math.pow(h.y-a.y,2))};a.getAngleBetweenPoints=function(a,h){return Math.atan2(h.x-a.x,h.y-a.y)}})(a||(a={}));return a});l(a,"Core/Geometry/CircleUtilities.js",[a["Core/Geometry/GeometryUtilities.js"]],function(a){const {getAngleBetweenPoints:h,
getCenterOfPoints:g,getDistanceBetweenPoints:z}=a;var u;(function(a){function v(a,e){e=Math.pow(10,e);return Math.round(a*e)/e}function t(a){if(0>=a)throw Error("radius of circle must be a positive number.");return Math.PI*a*a}function k(a,e){return a*a*Math.acos(1-e/a)-(a-e)*Math.sqrt(e*(2*a-e))}function u(a,e){var m=z(a,e),n=a.r,h=e.r,p=[];if(m<n+h&&m>Math.abs(n-h)){n*=n;var g=(n-h*h+m*m)/(2*m);h=Math.sqrt(n-g*g);n=a.x;p=e.x;a=a.y;const k=e.y;e=n+g*(p-n)/m;g=a+g*(k-a)/m;a=h/m*-(k-a);m=h/m*-(p-n);
p=[{x:v(e+a,14),y:v(g-m,14)},{x:v(e-a,14),y:v(g+m,14)}]}return p}function l(a){return a.reduce((a,h,g,p)=>{p=p.slice(g+1).reduce((a,e,p,m)=>{const n=[g,p+g+1];return a.concat(u(h,e).map(a=>{a.indexes=n;return a}))},[]);return a.concat(p)},[])}function y(a,e){return z(a,e)<=e.r+1e-10}function A(a,e){return!e.some(function(e){return!y(a,e)})}function D(a){return l(a).filter(function(e){return A(e,a)})}a.round=v;a.getAreaOfCircle=t;a.getCircularSegmentArea=k;a.getOverlapBetweenCircles=function(a,e,h){var g=
0;h<a+e&&(h<=Math.abs(e-a)?g=t(a<e?a:e):(g=(a*a-e*e+h*h)/(2*h),h-=g,g=k(a,a-g)+k(e,e-h)),g=v(g,14));return g};a.getCircleCircleIntersection=u;a.getCirclesIntersectionPoints=l;a.isCircle1CompletelyOverlappingCircle2=function(a,e){return z(a,e)+e.r<a.r+1e-10};a.isPointInsideCircle=y;a.isPointInsideAllCircles=A;a.isPointOutsideAllCircles=function(a,e){return!e.some(function(e){return y(a,e)})};a.getCirclesIntersectionPolygon=D;a.getAreaOfIntersectionBetweenCircles=function(a){var e=D(a);let k;if(1<e.length){const n=
g(e);e=e.map(function(a){a.angle=h(n,a);return a}).sort(function(a,e){return e.angle-a.angle});const m=e[e.length-1];e=e.reduce(function(e,k){const {startPoint:m}=e,n=g([m,k]),p=k.indexes.filter(function(a){return-1<m.indexes.indexOf(a)}).reduce(function(q,b){b=a[b];var d=h(b,k);const c=h(b,m);d=c-(c-d+(c<d?2*Math.PI:0))/2;d=z(n,{x:b.x+b.r*Math.sin(d),y:b.y+b.r*Math.cos(d)});({r:b}=b);d>2*b&&(d=2*b);if(!q||q.width>d)q={r:b,largeArc:d>b?1:0,width:d,x:k.x,y:k.y};return q},null);if(p){const {r:a}=p;
e.arcs.push(["A",a,a,0,p.largeArc,1,p.x,p.y]);e.startPoint=k}return e},{startPoint:m,arcs:[]}).arcs;0!==e.length&&1!==e.length&&(e.unshift(["M",m.x,m.y]),k={center:n,d:e})}return k}})(u||(u={}));return u});l(a,"Series/DrawPointUtilities.js",[a["Core/Utilities.js"]],function(a){return{draw:function(a,g){const {animatableAttribs:h,onComplete:u,css:l,renderer:v}=g,t=a.series&&a.series.chart.hasRendered?void 0:a.series&&a.series.options.animation;let k=a.graphic;g.attribs=Object.assign(Object.assign({},
g.attribs),{"class":a.getClassName()})||{};if(a.shouldDraw())k||(a.graphic=k="text"===g.shapeType?v.text():v[g.shapeType](g.shapeArgs||{}),k.add(g.group)),l&&k.css(l),k.attr(g.attribs).animate(h,g.isNew?!1:t,u);else if(k){const g=()=>{a.graphic=k=k&&k.destroy();"function"===typeof u&&u()};Object.keys(h).length?k.animate(h,void 0,()=>g()):g()}}}});l(a,"Series/Venn/VennPoint.js",[a["Core/Series/SeriesRegistry.js"],a["Core/Utilities.js"]],function(a,h){({seriesTypes:{scatter:{prototype:{pointClass:a}}}}=
a);const {isNumber:g}=h;class l extends a{constructor(){super(...arguments);this.series=this.options=void 0}isValid(){return g(this.value)}shouldDraw(){return!!this.shapeArgs}}return l});l(a,"Series/Venn/VennUtils.js",[a["Core/Geometry/CircleUtilities.js"],a["Core/Geometry/GeometryUtilities.js"],a["Core/Utilities.js"]],function(a,h,g){function l(a){const b={};a.filter(a=>2===a.sets.length).forEach(a=>{a.sets.forEach((c,f,d)=>{H(b[c])||(b[c]={totalOverlap:0,overlapping:{}});b[c]={totalOverlap:(b[c].totalOverlap||
0)+a.value,overlapping:Object.assign(Object.assign({},b[c].overlapping||{}),{[d[1-f]]:a.value})}})});a.filter(k).forEach(a=>{G(a,b[a.sets[0]])});return a}function u(a,b,d,c,f){let x=a(b),q=a(d);f=f||100;c=c||1e-10;let e=d-b,g=1,w,h;if(b>=d)throw Error("a must be smaller than b.");if(0<x*q)throw Error("f(a) and f(b) must have opposite signs.");if(0===x)w=b;else if(0===q)w=d;else for(;g++<=f&&0!==h&&e>c;)e=(d-b)/2,w=b+e,h=a(w),0<x*h?b=w:d=w;return w}function A(a){a=a.slice(0,-1);const b=a.length,d=
[],c=function(a,b){a.sum+=b[a.i];return a};for(let f=0;f<b;f++)d[f]=a.reduce(c,{sum:0,i:f}).sum/b;return d}function v(a,b,d){let c=a+b;return 0>=d?c:J(a<b?a:b)<=d?0:u(c=>{c=p(a,b,c);return d-c},0,c)}function t(a){var b=0;2===a.length&&(b=a[0],a=a[1],b=p(b.r,a.r,E(b,a)));return b}function k(a){return C(a.sets)&&1===a.sets.length}function y(a){const b={};return H(a)&&B(a.value)&&-1<a.value&&C(a.sets)&&0<a.sets.length&&!a.sets.some(function(a){let c=!1;!b[a]&&F(a)?b[a]=!0:c=!0;return c})}function L(a,
b){return b.reduce(function(b,c){var f=0;1<c.sets.length&&(f=c.value,c=t(c.sets.map(function(b){return a[b]})),c=f-c,f=Math.round(c*c*1E11)/1E11);return b+f},0)}function I(a,b){return"undefined"!==typeof b.totalOverlap&&"undefined"!==typeof a.totalOverlap?b.totalOverlap-a.totalOverlap:NaN}const {getAreaOfCircle:J,getCircleCircleIntersection:D,getOverlapBetweenCircles:p,isPointInsideAllCircles:e,isPointInsideCircle:m,isPointOutsideAllCircles:n}=a,{getDistanceBetweenPoints:E}=h,{extend:G,isArray:C,
isNumber:B,isObject:H,isString:F}=g;return{geometry:h,geometryCircles:a,addOverlapToSets:l,getCentroid:A,getDistanceBetweenCirclesByOverlap:v,getLabelWidth:function(a,b,d){const c=b.reduce((a,b)=>Math.min(b.r,a),Infinity),f=d.filter(function(b){return!m(a,b)});d=function(c,d){return u(function(x){var r={x:a.x+d*x,y:a.y};r=e(r,b)&&n(r,f);return-(c-x)+(r?0:Number.MAX_VALUE)},0,c)};return 2*Math.min(d(c,-1),d(c,1))},getMarginFromCircles:function(a,b,d){b=b.reduce(function(b,f){f=f.r-E(a,f);return f<=
b?f:b},Number.MAX_VALUE);return b=d.reduce(function(b,f){f=E(a,f)-f.r;return f<=b?f:b},b)},isSet:k,layoutGreedyVenn:function(a){const b=[],d={};a.filter(function(a){return 1===a.sets.length}).forEach(function(a){d[a.sets[0]]=a.circle={x:Number.MAX_VALUE,y:Number.MAX_VALUE,r:Math.sqrt(a.value/Math.PI)}});const c=function(a,c){const f=a.circle;f&&(f.x=c.x,f.y=c.y);b.push(a)};l(a);const f=a.filter(k).sort(I);c(f.shift(),{x:0,y:0});const x=a.filter(function(a){return 2===a.sets.length});f.forEach(function(a){const f=
a.circle;if(f){var e=f.r,g=a.overlapping,h=b.reduce((a,c,h)=>{const r=c.circle;if(!r||!g)return a;const w=v(e,r.r,g[c.sets[0]]);let K=[{x:r.x+w,y:r.y},{x:r.x-w,y:r.y},{x:r.x,y:r.y+w},{x:r.x,y:r.y-w}];b.slice(h+1).forEach(function(a){const b=a.circle;b&&(a=v(e,b.r,g[a.sets[0]]),K=K.concat(D({x:r.x,y:r.y,r:w},{x:b.x,y:b.y,r:a})))});K.forEach(function(b){f.x=b.x;f.y=b.y;const c=L(d,x);c<a.loss&&(a.loss=c,a.coordinates=b)});return a},{loss:Number.MAX_VALUE,coordinates:void 0});c(a,h.coordinates)}});return d},
loss:L,nelderMead:function(a,b){const d=function(a,b){return a.fx-b.fx},c=(a,b,c,f)=>b.map((b,d)=>a*b+c*f[d]),f=(b,c)=>{c.fx=a(c);b[b.length-1]=c;return b},x=b=>{const f=b[0];return b.map(b=>{b=c(.5,f,.5,b);b.fx=a(b);return b})},e=(b,f,d,e)=>{b=c(d,b,e,f);b.fx=a(b);return b};b=(b=>{const c=b.length,f=Array(c+1);f[0]=b;f[0].fx=a(b);for(let d=0;d<c;++d){const c=b.slice();c[d]=c[d]?1.05*c[d]:.001;c.fx=a(c);f[d+1]=c}return f})(b);for(let a=0;100>a;a++){b.sort(d);var g=b[b.length-1],h=A(b);const a=e(h,
g,2,-1);a.fx<b[0].fx?(g=e(h,g,3,-2),b=f(b,g.fx<a.fx?g:a)):a.fx>=b[b.length-2].fx?a.fx>g.fx?(h=e(h,g,.5,.5),b=h.fx<g.fx?f(b,h):x(b)):(h=e(h,g,1.5,-.5),b=h.fx<a.fx?f(b,h):x(b)):b=f(b,a)}return b[0]},processVennData:function(a,b){a=C(a)?a:[];const d=a.reduce(function(a,b){var c;if(c=b.sets)c=y(b)&&k(b)&&0<b.value;c&&-1===a.indexOf(b.sets[0])&&a.push(b.sets[0]);return a},[]).sort(),c=a.reduce(function(a,c){c.sets&&y(c)&&!c.sets.some(function(a){return-1===d.indexOf(a)})&&(a[c.sets.sort().join(b)]={sets:c.sets,
value:c.value||0});return a},{});d.reduce(function(a,c,d,e){e.slice(d+1).forEach(function(d){a.push(c+b+d)});return a},[]).forEach(function(a){if(!c[a]){const d={sets:a.split(b),value:0};c[a]=d}});return Object.keys(c).map(function(a){return c[a]})},sortByTotalOverlap:I}});l(a,"Series/Venn/VennSeries.js",[a["Core/Animation/AnimationUtilities.js"],a["Core/Color/Color.js"],a["Core/Geometry/CircleUtilities.js"],a["Series/DrawPointUtilities.js"],a["Core/Geometry/GeometryUtilities.js"],a["Core/Series/SeriesRegistry.js"],
a["Series/Venn/VennPoint.js"],a["Series/Venn/VennUtils.js"],a["Core/Utilities.js"]],function(a,h,g,l,u,A,v,t,k){const {animObject:y}=a,{parse:z}=h,{getAreaOfIntersectionBetweenCircles:I,getCirclesIntersectionPolygon:J,isCircle1CompletelyOverlappingCircle2:D,isPointInsideAllCircles:p,isPointOutsideAllCircles:e}=g,{getCenterOfPoints:m}=u,{seriesTypes:{scatter:n}}=A,{addEvent:E,extend:G,isArray:C,isNumber:B,isObject:H,merge:F}=k;class q extends n{constructor(){super(...arguments);this.points=this.options=
this.mapOfIdToRelation=this.data=void 0}static getLabelPosition(a,d){var b=a.reduce((b,c)=>{const f=c.r/2;return[{x:c.x,y:c.y},{x:c.x+f,y:c.y},{x:c.x-f,y:c.y},{x:c.x,y:c.y+f},{x:c.x,y:c.y-f}].reduce((b,c)=>{const f=t.getMarginFromCircles(c,a,d);b.margin<f&&(b.point=c,b.margin=f);return b},b)},{point:void 0,margin:-Number.MAX_VALUE}).point;b=t.nelderMead(function(b){return-t.getMarginFromCircles({x:b[0],y:b[1]},a,d)},[b.x,b.y]);b={x:b[0],y:b[1]};p(b,a)&&e(b,d)||(b=1<a.length?m(J(a)):{x:a[0].x,y:a[0].y});
return b}static getLabelValues(a,d){const b=a.sets,f=d.reduce((a,c)=>{const d=-1<b.indexOf(c.sets[0]);c.circle&&a[d?"internal":"external"].push(c.circle);return a},{internal:[],external:[]});f.external=f.external.filter(a=>f.internal.some(b=>!D(a,b)));a=q.getLabelPosition(f.internal,f.external);d=t.getLabelWidth(a,f.internal,f.external);return{position:a,width:d}}static layout(a){const b={},c={};if(0<a.length){const d=t.layoutGreedyVenn(a),e=a.filter(t.isSet);a.forEach(function(a){var f=a.sets;const g=
f.join();if(f=t.isSet(a)?d[g]:I(f.map(a=>d[a])))b[g]=f,c[g]=q.getLabelValues(a,e)})}return{mapOfIdToShape:b,mapOfIdToLabelValues:c}}static getScale(a,d,c){var b=c.bottom-c.top;const e=c.right-c.left;b=Math.min(0<e?1/e*a:1,0<b?1/b*d:1);return{scale:b,centerX:a/2-(c.right+c.left)/2*b,centerY:d/2-(c.top+c.bottom)/2*b}}static updateFieldBoundaries(a,d){const b=d.x-d.r,f=d.x+d.r,e=d.y+d.r;d=d.y-d.r;if(!B(a.left)||a.left>b)a.left=b;if(!B(a.right)||a.right<f)a.right=f;if(!B(a.top)||a.top>d)a.top=d;if(!B(a.bottom)||
a.bottom<e)a.bottom=e;return a}animate(a){if(!a){const a=y(this.options.animation);this.points.forEach(function(b){const c=b.shapeArgs;if(b.graphic&&c){const d={},e={};c.d?d.opacity=.001:(d.r=0,e.r=c.r);b.graphic.attr(d).animate(e,a);c.d&&setTimeout(function(){b&&b.graphic&&b.graphic.animate({opacity:1})},a.duration)}},this)}}drawPoints(){const a=this,d=a.chart,c=a.group,e=d.renderer;(a.points||[]).forEach(function(b){const f={zIndex:C(b.sets)?b.sets.length:0},g=b.shapeArgs;d.styledMode||G(f,a.pointAttribs(b,
b.state));l.draw(b,{isNew:!b.graphic,animatableAttribs:g,attribs:f,group:c,renderer:e,shapeType:g&&g.d?"path":"circle"})})}init(){n.prototype.init.apply(this,arguments);delete this.opacity}pointAttribs(a,d){const b=this.options||{};a=F(b,{color:a&&a.color},a&&a.options||{},d&&b.states[d]||{});return{fill:z(a.color).brighten(a.brightness).get(),opacity:a.opacity,stroke:a.borderColor,"stroke-width":a.borderWidth,dashstyle:a.borderDashStyle}}translate(){var a=this.chart;this.processedXData=this.xData;
this.generatePoints();var d=t.processVennData(this.options.data,q.splitter);const {mapOfIdToShape:c,mapOfIdToLabelValues:e}=q.layout(d);d=Object.keys(c).filter(function(a){return(a=c[a])&&B(a.r)}).reduce(function(a,b){return q.updateFieldBoundaries(a,c[b])},{top:0,bottom:0,left:0,right:0});a=q.getScale(a.plotWidth,a.plotHeight,d);const g=a.scale,h=a.centerX,k=a.centerY;this.points.forEach(function(a){let b=C(a.sets)?a.sets:[];var d=b.join(),f=c[d];let m;var l=e[d]||{};d=l.width;l=l.position;let n=
a.options&&a.options.dataLabels;f&&(f.r?m={x:h+f.x*g,y:k+f.y*g,r:f.r*g}:f.d&&(f=f.d,f.forEach(a=>{"M"===a[0]?(a[1]=h+a[1]*g,a[2]=k+a[2]*g):"A"===a[0]&&(a[1]*=g,a[2]*=g,a[6]=h+a[6]*g,a[7]=k+a[7]*g)}),m={d:f}),l?(l.x=h+l.x*g,l.y=k+l.y*g):l={},B(d)&&(d=Math.round(d*g)));a.shapeArgs=m;l&&m&&(a.plotX=l.x,a.plotY=l.y);d&&m&&(a.dlOptions=F(!0,{style:{width:d}},H(n,!0)?n:void 0));a.name=a.options.name||b.join("\u2229")})}}q.splitter="highcharts-split";q.defaultOptions=F(n.defaultOptions,{borderColor:"#cccccc",
borderDashStyle:"solid",borderWidth:1,brighten:0,clip:!1,colorByPoint:!0,dataLabels:{enabled:!0,verticalAlign:"middle",formatter:function(){return this.point.name}},inactiveOtherPoints:!0,marker:!1,opacity:.75,showInLegend:!1,legendType:"point",states:{hover:{opacity:1,borderColor:"#333333"},select:{color:"#cccccc",borderColor:"#000000",animation:!1},inactive:{opacity:.075}},tooltip:{pointFormat:"{point.name}: {point.value}"},legendSymbol:"rectangle"});G(q.prototype,{axisTypes:[],directTouch:!0,isCartesian:!1,
pointArrayMap:["value"],pointClass:v,utils:t});A.registerSeriesType("venn",q);"";E(q,"afterSetOptions",function(a){const b=a.options.states;this.is("venn")&&Object.keys(b).forEach(function(a){b[a].halo=!1})});return q});l(a,"masters/modules/venn.src.js",[],function(){})});
//# sourceMappingURL=venn.js.map