(function(e){function t(t){for(var a,r,i=t[0],l=t[1],s=t[2],u=0,d=[];u<i.length;u++)r=i[u],Object.prototype.hasOwnProperty.call(o,r)&&o[r]&&d.push(o[r][0]),o[r]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(e[a]=l[a]);b&&b(t);while(d.length)d.shift()();return c.push.apply(c,s||[]),n()}function n(){for(var e,t=0;t<c.length;t++){for(var n=c[t],a=!0,r=1;r<n.length;r++){var i=n[r];0!==o[i]&&(a=!1)}a&&(c.splice(t--,1),e=l(l.s=n[0]))}return e}var a={},r={app:0},o={app:0},c=[];function i(e){return l.p+"js/"+({}[e]||e)+"."+{"chunk-1d4f765b":"43414890","chunk-2d217357":"86fec210","chunk-2d22d746":"b81f0a08","chunk-3a96c604":"42c90387","chunk-5a4d6f42":"2aea1852","chunk-5ccdcb5a":"384b68f4","chunk-6f3c97b8":"2bd5c0db","chunk-86faf7c6":"746af842","chunk-bfa026e6":"e1bc1664","chunk-ecaa67c0":"ffd37e8d"}[e]+".js"}function l(t){if(a[t])return a[t].exports;var n=a[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,l),n.l=!0,n.exports}l.e=function(e){var t=[],n={"chunk-1d4f765b":1,"chunk-3a96c604":1,"chunk-5a4d6f42":1,"chunk-5ccdcb5a":1,"chunk-6f3c97b8":1,"chunk-86faf7c6":1,"chunk-bfa026e6":1,"chunk-ecaa67c0":1};r[e]?t.push(r[e]):0!==r[e]&&n[e]&&t.push(r[e]=new Promise((function(t,n){for(var a="css/"+({}[e]||e)+"."+{"chunk-1d4f765b":"e5865a9a","chunk-2d217357":"31d6cfe0","chunk-2d22d746":"31d6cfe0","chunk-3a96c604":"8a8d645a","chunk-5a4d6f42":"0a9f8eb5","chunk-5ccdcb5a":"04b433c5","chunk-6f3c97b8":"dbd005bd","chunk-86faf7c6":"e5865a9a","chunk-bfa026e6":"baff913e","chunk-ecaa67c0":"f9ca6e97"}[e]+".css",o=l.p+a,c=document.getElementsByTagName("link"),i=0;i<c.length;i++){var s=c[i],u=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(u===a||u===o))return t()}var d=document.getElementsByTagName("style");for(i=0;i<d.length;i++){s=d[i],u=s.getAttribute("data-href");if(u===a||u===o)return t()}var b=document.createElement("link");b.rel="stylesheet",b.type="text/css",b.onload=t,b.onerror=function(t){var a=t&&t.target&&t.target.src||o,c=new Error("Loading CSS chunk "+e+" failed.\n("+a+")");c.code="CSS_CHUNK_LOAD_FAILED",c.request=a,delete r[e],b.parentNode.removeChild(b),n(c)},b.href=o;var p=document.getElementsByTagName("head")[0];p.appendChild(b)})).then((function(){r[e]=0})));var a=o[e];if(0!==a)if(a)t.push(a[2]);else{var c=new Promise((function(t,n){a=o[e]=[t,n]}));t.push(a[2]=c);var s,u=document.createElement("script");u.charset="utf-8",u.timeout=120,l.nc&&u.setAttribute("nonce",l.nc),u.src=i(e);var d=new Error;s=function(t){u.onerror=u.onload=null,clearTimeout(b);var n=o[e];if(0!==n){if(n){var a=t&&("load"===t.type?"missing":t.type),r=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+a+": "+r+")",d.name="ChunkLoadError",d.type=a,d.request=r,n[1](d)}o[e]=void 0}};var b=setTimeout((function(){s({type:"timeout",target:u})}),12e4);u.onerror=u.onload=s,document.head.appendChild(u)}return Promise.all(t)},l.m=e,l.c=a,l.d=function(e,t,n){l.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},l.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},l.t=function(e,t){if(1&t&&(e=l(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(l.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)l.d(n,a,function(t){return e[t]}.bind(null,a));return n},l.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return l.d(t,"a",t),t},l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},l.p="/",l.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],u=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var b=u;c.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"56d7":function(e,t,n){"use strict";n.r(t);n("14d9");var a=n("7a23"),r=n("5502");const o=Object(a["g"])("head",null,[Object(a["g"])("link",{rel:"stylesheet",href:"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"})],-1),c={class:"container"};function i(e,t,n,r,i,l){const s=Object(a["B"])("Navbar"),u=Object(a["B"])("router-view");return Object(a["t"])(),Object(a["f"])(a["a"],null,[o,Object(a["g"])("header",null,[Object(a["i"])(s)]),Object(a["g"])("main",c,[Object(a["i"])(u)])],64)}var l=n("6541"),s=n.n(l);const u=e=>(Object(a["w"])("data-v-e269e930"),e=e(),Object(a["u"])(),e),d={class:"navbar fixed-top navbar-expand-lg navbar-light"},b={class:"container-fluid"},p=u(()=>Object(a["g"])("button",{class:"navbar-toggler",type:"button","data-bs-toggle":"collapse","data-bs-target":"#navbarSupportedContent","aria-controls":"navbarSupportedContent","aria-expanded":"false","aria-label":"Toggle navigation"},[Object(a["g"])("span",{class:"navbar-toggler-icon"})],-1)),g={class:"collapse navbar-collapse",id:"navbarSupportedContent"},f={class:"navbar-nav me-auto mb-2 mb-lg-0"},h={key:0,class:"nav-item"},m={key:1,class:"nav-item"},O={class:"nav-item"},j={key:2,class:"nav-item"},v={key:0,class:"d-flex justify-content-end"},k={class:"dropdown"},y=u(()=>Object(a["g"])("img",{class:"navProfilePic dropdown-toggle",src:s.a,alt:"../../assets/genericProfile.png",id:"navbarDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"},null,-1)),P={class:"dropdown-menu dropdown-menu-end"},w={class:"dropdown-header"},S=u(()=>Object(a["g"])("li",null,[Object(a["g"])("hr",{class:"dropdown-divider"})],-1)),I={key:1,class:"d-flex justify-content-center"},$={class:"mb-2 mb-lg-0"};function N(e,t,n,r,o,c){const i=Object(a["B"])("NavbarLink");return Object(a["t"])(),Object(a["f"])("nav",d,[Object(a["g"])("div",b,[Object(a["i"])(i,{class:"navbar-brand",link:"/",displayName:"Linked Care"}),p,Object(a["g"])("div",g,[Object(a["g"])("ul",f,[e.$AuthorizedRole([1,2])?(Object(a["t"])(),Object(a["f"])("li",h,[Object(a["i"])(i,{class:"nav-link",link:"/patients",displayName:"Patienten"})])):Object(a["e"])("",!0),e.$AuthorizedRole([3])?(Object(a["t"])(),Object(a["f"])("li",m,[Object(a["i"])(i,{class:"nav-link",link:"/patient/"+e.$getUserId(),displayName:"Meine Daten"},null,8,["link"])])):Object(a["e"])("",!0),Object(a["g"])("li",O,[Object(a["i"])(i,{class:"nav-link",link:"/about",displayName:"About"})]),e.$AuthorizedRole([2])?(Object(a["t"])(),Object(a["f"])("li",j,[Object(a["i"])(i,{class:"btn btn-outline-primary",link:"/open-discharges",displayName:"Zum bearbeiten"})])):Object(a["e"])("",!0)]),e.$isLoggedIn()?(Object(a["t"])(),Object(a["f"])("div",v,[Object(a["g"])("div",k,[y,Object(a["g"])("ul",P,[Object(a["g"])("li",null,[Object(a["g"])("span",w,Object(a["E"])(e.$getUserEmail())+" - ["+Object(a["E"])(e.$getUserRoleString())+"]",1)]),S,Object(a["g"])("li",null,[Object(a["g"])("button",{class:"dropdown-item",onClick:t[0]||(t[0]=t=>e.$logout())},"Logout")])])])])):Object(a["e"])("",!0),e.$isLoggedIn()?Object(a["e"])("",!0):(Object(a["t"])(),Object(a["f"])("div",I,[Object(a["g"])("div",$,[Object(a["i"])(i,{link:"/login",displayName:"Login",class:"btn btn-primary"})])]))])])])}function A(e,t,n,r,o,c){const i=Object(a["B"])("router-link");return Object(a["t"])(),Object(a["d"])(i,{to:n.link},{default:Object(a["I"])(()=>[Object(a["h"])(Object(a["E"])(n.displayName),1)]),_:1},8,["to"])}var E={props:{link:{type:String,required:!0},displayName:{type:String,required:!0}},setup(){}},L=n("6b0d"),U=n.n(L);const C=U()(E,[["render",A]]);var x=C,R={components:{NavbarLink:x},setup(){}};n("86c1");const z=U()(R,[["render",N],["__scopeId","data-v-e269e930"]]);var _=z,D={name:"App",components:{Navbar:_}};n("ae3e");const T=U()(D,[["render",i]]);var B=T,F=(n("ab8b"),n("7b17"),n("bc3a")),H=n.n(F),J=n("130e"),M=n("6605");const q={class:"home"},K={key:0};function V(e,t,n,r,o,c){return Object(a["t"])(),Object(a["f"])("div",q,[e.$isLoggedIn()?(Object(a["t"])(),Object(a["f"])("p",K,"Hello your are logged in as "+Object(a["E"])(e.$getUserEmail()),1)):Object(a["e"])("",!0)])}var Z={name:"Home",beforeCreate(){switch(this.$isLoggedIn()||this.$router.push("/login"),this.$getUserRole()){case 1:this.$router.push("/patients");break;case 2:this.$router.push("/open-discharges");break;case 3:this.$router.push("/patient/"+this.$getUserId());break;case 4:this.$router.push("/patients");break}}};const G=U()(Z,[["render",V]]);var Q=G;const W=[{path:"/",name:"Home",component:Q,meta:{title:"Home"}},{path:"/about",name:"About",component:()=>n.e("chunk-2d22d746").then(n.bind(null,"f820")),meta:{title:"About"}},{path:"/patients",name:"Patienten",component:()=>n.e("chunk-1d4f765b").then(n.bind(null,"a474")),meta:{title:"Patienten"}},{path:"/login",name:"Login",component:()=>n.e("chunk-ecaa67c0").then(n.bind(null,"a55b")),meta:{title:"Login"}},{path:"/profile",name:"Profile",component:()=>n.e("chunk-2d217357").then(n.bind(null,"c66d")),meta:{title:"Profile"}},{path:"/patient/:id",name:"Patient",component:()=>n.e("chunk-6f3c97b8").then(n.bind(null,"a58e")),meta:{title:"Patient"}},{path:"/patient/:id/createPrescription",name:"CreatePrescription",component:()=>n.e("chunk-5a4d6f42").then(n.bind(null,"d1a1")),meta:{title:"Neues Rezept"}},{path:"/patient/:id/createPrescriptionFromDischarge/:dischargeId",name:"CreatePrescriptionFromDischarge",component:()=>n.e("chunk-5a4d6f42").then(n.bind(null,"d1a1")),meta:{title:"Neues Rezept"}},{path:"/patient/:id/createDischargeSummary",name:"CreateDischargeSummary",component:()=>n.e("chunk-5ccdcb5a").then(n.bind(null,"7d1c")),meta:{title:"Neues Entlassungsbrief"}},{path:"/patient/:patientId/discharge-summary/:dischargeId",name:"ViewDischargeSummary",component:()=>n.e("chunk-bfa026e6").then(n.bind(null,"481c")),meta:{title:"Entlassungsbrief"}},{path:"/patient/:patientId/prescription/:prescriptionId",name:"ViewPrescription",component:()=>n.e("chunk-3a96c604").then(n.bind(null,"6619")),meta:{title:"Rezept"}},{path:"/open-discharges",name:"OpenDischarges",component:()=>n.e("chunk-86faf7c6").then(n.bind(null,"520a")),meta:{title:"Offene Aufgaben"}}],X=Object(M["a"])({history:Object(M["b"])("/"),routes:W});X.beforeEach((e,t,n)=>{window.document.title=(e.meta&&e.meta.title?e.meta.title:"Home")+" | Linked Care",n()});var Y=X,ee=n("8857"),te=n("4edd");let ne=a["c"](B);ne.config.globalProperties.$baseApiUrl="https://ubt-n6.kvm.w3.cs.technikum-wien.at",ne.config.globalProperties.$apiUrl=ne.config.globalProperties.$baseApiUrl+"/fhir",ne.config.globalProperties.$logout=()=>{localStorage.removeItem("loggedIn"),localStorage.removeItem("user"),console.log("[INFO] User logout"),window.location.href="/"},ne.config.globalProperties.$getUserEmail=()=>null!=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).email:"",ne.config.globalProperties.$getUserId=()=>null!=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).id:"",ne.config.globalProperties.$getUserRole=()=>null!=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).role:0,ne.config.globalProperties.$AuthorizedRole=e=>{let t=ne.config.globalProperties.$getUserRole();for(let n of e)if(n===t)return!0;return!1},ne.config.globalProperties.$redirectNotAuthorizedToLogin=e=>ne.config.globalProperties.$AuthorizedRole(e)?(console.log("[INFO] User is authorized to open this page"),!0):(console.log("[INFO] User is not authorized to open this page"),Y.push("/login"),!1),ne.config.globalProperties.$getUserRoleString=()=>{let e=0;switch(null!=localStorage.getItem("user")&&(e=JSON.parse(localStorage.getItem("user")).role),e){case 1:return"Krankenhaus";case 2:return"Arzt";case 3:return"Patient";case 4:return"Apotheke";case 0:return"undefined"}},ne.config.globalProperties.$isLoggedIn=()=>null!==localStorage.getItem("loggedIn"),ne.component(te["a"].name,te["a"]),ne.use(Y,J["a"],H.a,r["a"],ee["a"]).mount("#app")},6541:function(e,t,n){e.exports=n.p+"img/genericProfile.cb276d76.png"},"86c1":function(e,t,n){"use strict";n("9d6b")},"9bb8":function(e,t,n){},"9d6b":function(e,t,n){},ae3e:function(e,t,n){"use strict";n("9bb8")}});
//# sourceMappingURL=app.29fcd0c8.js.map