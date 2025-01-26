"use strict";(self["webpackChunklinked_care_web_client"]=self["webpackChunklinked_care_web_client"]||[]).push([[363],{1363:function(t,e,r){r.r(e),r.d(e,{default:function(){return g}});var n=r(641),o=r(3751),a=r(33);const i={class:"container"},u={class:"row"},s={class:"login col-6 card"},l={class:"mb-3"},c={class:"mb-3"},f={key:0};function p(t,e,r,p,h,d){return(0,n.uX)(),(0,n.CE)("div",i,[(0,n.Lk)("div",u,[e[6]||(e[6]=(0,n.Lk)("h2",null,"Login",-1)),e[7]||(e[7]=(0,n.Lk)("div",{class:"col-3"},null,-1)),(0,n.Lk)("div",s,[(0,n.Lk)("form",{onSubmit:e[2]||(e[2]=(0,o.D$)(((...t)=>d.validateForm&&d.validateForm(...t)),["prevent"]))},[(0,n.Lk)("div",l,[e[3]||(e[3]=(0,n.Lk)("label",{for:"inputEmail",class:"form-label"},"Email-Adresse",-1)),(0,n.bo)((0,n.Lk)("input",{type:"email","onUpdate:modelValue":e[0]||(e[0]=t=>h.loginData.email=t),class:"form-control",id:"inputEmail",required:""},null,512),[[o.Jo,h.loginData.email]])]),(0,n.Lk)("div",c,[e[4]||(e[4]=(0,n.Lk)("label",{for:"inputPassword",class:"form-label"},"Passwort",-1)),(0,n.bo)((0,n.Lk)("input",{type:"password","onUpdate:modelValue":e[1]||(e[1]=t=>h.loginData.password=t),class:"form-control",id:"inputPassword",required:""},null,512),[[o.Jo,h.loginData.password]])]),e[5]||(e[5]=(0,n.Lk)("button",{type:"submit",class:"btn btn-secondary mb-3"},"Einloggen",-1)),h.errorMsg.length?((0,n.uX)(),(0,n.CE)("b",f,[(0,n.Lk)("ul",null,[((0,n.uX)(!0),(0,n.CE)(n.FK,null,(0,n.pI)(h.errorMsg,((t,e)=>((0,n.uX)(),(0,n.CE)("li",{key:e},(0,a.v_)(t),1)))),128))])])):(0,n.Q3)("",!0)],32)])])])}r(4114),r(4603),r(7566),r(8721);var h={setup(){},data(){return{loginData:{email:"",password:""},errorMsg:[]}},methods:{validateForm(){this.errorMsg=[],""!=this.loginData.email&&null!=this.loginData.email||this.errorMsg.push("Bitte geben Sie Ihre Email-Adresse an!"),""!=this.loginData.password&&null!=this.loginData.password||this.errorMsg.push("Bitte geben Sie Ihr Passwort ein!"),this.errorMsg.length||this.loginUser()},loginUser(){const t=new Headers;t.append("Content-Type","application/x-www-form-urlencoded");const e=new URLSearchParams;e.append("grant_type","password"),e.append("client_id","JavaBackend"),e.append("username",this.loginData.email),e.append("password",this.loginData.password);const r={method:"POST",headers:t,body:e,redirect:"follow"};let n,o;fetch("http://172.29.16.61:8080/realms/data-facade-app/protocol/openid-connect/token",r).then((t=>t.json())).then((t=>{o=t})).then((()=>{n=o["access_token"]})).then((()=>{document.cookie=`${n};SameSite=None Secure`})).catch((t=>console.error(t)))}}},d=r(6262);const v=(0,d.A)(h,[["render",p],["__scopeId","data-v-ab06f5c8"]]);var g=v},9617:function(t,e,r){var n=r(5397),o=r(5610),a=r(6198),i=function(t){return function(e,r,i){var u=n(e),s=a(u);if(0===s)return!t&&-1;var l,c=o(i,s);if(t&&r!==r){while(s>c)if(l=u[c++],l!==l)return!0}else for(;s>c;c++)if((t||c in u)&&u[c]===r)return t||c||0;return!t&&-1}};t.exports={includes:i(!0),indexOf:i(!1)}},4527:function(t,e,r){var n=r(3724),o=r(4376),a=TypeError,i=Object.getOwnPropertyDescriptor,u=n&&!function(){if(void 0!==this)return!0;try{Object.defineProperty([],"length",{writable:!1}).length=1}catch(t){return t instanceof TypeError}}();t.exports=u?function(t,e){if(o(t)&&!i(t,"length").writable)throw new a("Cannot set read only .length");return t.length=e}:function(t,e){return t.length=e}},7740:function(t,e,r){var n=r(9297),o=r(5031),a=r(7347),i=r(4913);t.exports=function(t,e,r){for(var u=o(e),s=i.f,l=a.f,c=0;c<u.length;c++){var f=u[c];n(t,f)||r&&n(r,f)||s(t,f,l(e,f))}}},6837:function(t){var e=TypeError,r=9007199254740991;t.exports=function(t){if(t>r)throw e("Maximum allowed index exceeded");return t}},8727:function(t){t.exports=["constructor","hasOwnProperty","isPrototypeOf","propertyIsEnumerable","toLocaleString","toString","valueOf"]},6518:function(t,e,r){var n=r(4576),o=r(7347).f,a=r(6699),i=r(6840),u=r(9433),s=r(7740),l=r(2796);t.exports=function(t,e){var r,c,f,p,h,d,v=t.target,g=t.global,m=t.stat;if(c=g?n:m?n[v]||u(v,{}):n[v]&&n[v].prototype,c)for(f in e){if(h=e[f],t.dontCallGetSet?(d=o(c,f),p=d&&d.value):p=c[f],r=l(g?f:v+(m?".":"#")+f,t.forced),!r&&void 0!==p){if(typeof h==typeof p)continue;s(h,p)}(t.sham||p&&p.sham)&&a(h,"sham",!0),i(c,f,h,t)}}},7055:function(t,e,r){var n=r(9504),o=r(9039),a=r(2195),i=Object,u=n("".split);t.exports=o((function(){return!i("z").propertyIsEnumerable(0)}))?function(t){return"String"===a(t)?u(t,""):i(t)}:i},4376:function(t,e,r){var n=r(2195);t.exports=Array.isArray||function(t){return"Array"===n(t)}},2796:function(t,e,r){var n=r(9039),o=r(4901),a=/#|\.prototype\./,i=function(t,e){var r=s[u(t)];return r===c||r!==l&&(o(e)?n(e):!!e)},u=i.normalize=function(t){return String(t).replace(a,".").toLowerCase()},s=i.data={},l=i.NATIVE="N",c=i.POLYFILL="P";t.exports=i},6198:function(t,e,r){var n=r(8014);t.exports=function(t){return n(t.length)}},741:function(t){var e=Math.ceil,r=Math.floor;t.exports=Math.trunc||function(t){var n=+t;return(n>0?r:e)(n)}},7347:function(t,e,r){var n=r(3724),o=r(9565),a=r(8773),i=r(6980),u=r(5397),s=r(6969),l=r(9297),c=r(5917),f=Object.getOwnPropertyDescriptor;e.f=n?f:function(t,e){if(t=u(t),e=s(e),c)try{return f(t,e)}catch(r){}if(l(t,e))return i(!o(a.f,t,e),t[e])}},8480:function(t,e,r){var n=r(1828),o=r(8727),a=o.concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return n(t,a)}},3717:function(t,e){e.f=Object.getOwnPropertySymbols},1828:function(t,e,r){var n=r(9504),o=r(9297),a=r(5397),i=r(9617).indexOf,u=r(421),s=n([].push);t.exports=function(t,e){var r,n=a(t),l=0,c=[];for(r in n)!o(u,r)&&o(n,r)&&s(c,r);while(e.length>l)o(n,r=e[l++])&&(~i(c,r)||s(c,r));return c}},8773:function(t,e){var r={}.propertyIsEnumerable,n=Object.getOwnPropertyDescriptor,o=n&&!r.call({1:2},1);e.f=o?function(t){var e=n(this,t);return!!e&&e.enumerable}:r},5031:function(t,e,r){var n=r(7751),o=r(9504),a=r(8480),i=r(3717),u=r(8551),s=o([].concat);t.exports=n("Reflect","ownKeys")||function(t){var e=a.f(u(t)),r=i.f;return r?s(e,r(t)):e}},5610:function(t,e,r){var n=r(1291),o=Math.max,a=Math.min;t.exports=function(t,e){var r=n(t);return r<0?o(r+e,0):a(r,e)}},5397:function(t,e,r){var n=r(7055),o=r(7750);t.exports=function(t){return n(o(t))}},1291:function(t,e,r){var n=r(741);t.exports=function(t){var e=+t;return e!==e||0===e?0:n(e)}},8014:function(t,e,r){var n=r(1291),o=Math.min;t.exports=function(t){var e=n(t);return e>0?o(e,9007199254740991):0}},655:function(t,e,r){var n=r(6955),o=String;t.exports=function(t){if("Symbol"===n(t))throw new TypeError("Cannot convert a Symbol value to a string");return o(t)}},2812:function(t){var e=TypeError;t.exports=function(t,r){if(t<r)throw new e("Not enough arguments");return t}},4114:function(t,e,r){var n=r(6518),o=r(8981),a=r(6198),i=r(4527),u=r(6837),s=r(9039),l=s((function(){return 4294967297!==[].push.call({length:4294967296},1)})),c=function(){try{Object.defineProperty([],"length",{writable:!1}).push()}catch(t){return t instanceof TypeError}},f=l||!c();n({target:"Array",proto:!0,arity:1,forced:f},{push:function(t){var e=o(this),r=a(e),n=arguments.length;u(r+n);for(var s=0;s<n;s++)e[r]=arguments[s],r++;return i(e,r),r}})},4603:function(t,e,r){var n=r(6840),o=r(9504),a=r(655),i=r(2812),u=URLSearchParams,s=u.prototype,l=o(s.append),c=o(s["delete"]),f=o(s.forEach),p=o([].push),h=new u("a=1&a=2&b=3");h["delete"]("a",1),h["delete"]("b",void 0),h+""!=="a=2"&&n(s,"delete",(function(t){var e=arguments.length,r=e<2?void 0:arguments[1];if(e&&void 0===r)return c(this,t);var n=[];f(this,(function(t,e){p(n,{key:e,value:t})})),i(e,1);var o,u=a(t),s=a(r),h=0,d=0,v=!1,g=n.length;while(h<g)o=n[h++],v||o.key===u?(v=!0,c(this,o.key)):d++;while(d<g)o=n[d++],o.key===u&&o.value===s||l(this,o.key,o.value)}),{enumerable:!0,unsafe:!0})},7566:function(t,e,r){var n=r(6840),o=r(9504),a=r(655),i=r(2812),u=URLSearchParams,s=u.prototype,l=o(s.getAll),c=o(s.has),f=new u("a=1");!f.has("a",2)&&f.has("a",void 0)||n(s,"has",(function(t){var e=arguments.length,r=e<2?void 0:arguments[1];if(e&&void 0===r)return c(this,t);var n=l(this,t);i(e,1);var o=a(r),u=0;while(u<n.length)if(n[u++]===o)return!0;return!1}),{enumerable:!0,unsafe:!0})},8721:function(t,e,r){var n=r(3724),o=r(9504),a=r(2106),i=URLSearchParams.prototype,u=o(i.forEach);n&&!("size"in i)&&a(i,"size",{get:function(){var t=0;return u(this,(function(){t++})),t},configurable:!0,enumerable:!0})}}]);
//# sourceMappingURL=363.6e530d2d.js.map