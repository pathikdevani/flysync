var $Qt = function($obj){
    var obj = $obj.objects;
    var parent = $obj.parent;
    var length = $obj.group;
    var each = $obj.each;
    if(length == undefined){
        length = 1;
    }else{
        length = length.length;
    }

    function $func(tag,goPrm){
        var list = Object.keys(goPrm);
        for(var i = 0;i < list.length;i++){
            var prop = list[i];
            if(prop == 'event'){
                var evObj = goPrm['event'];
                var listJ = Object.keys(evObj);
                for(var j = 0;j < listJ.length;j++) {
                    var evName = listJ[j];
                    var evFn = evObj[evName];
                    $(tag)[evName](evFn);
                }
            } else if(prop == 'text'){
                var evObj = goPrm['text'];
                $(tag).append(evObj);
            } else if(prop == 'val'){
                var evObj = goPrm['val'];
                $(tag).val(evObj);
            }else if(prop == 'css'){
                var evObj = goPrm['css'];
                //console.log(goPrm);
                $(tag).css(evObj);
            }else if(prop == 'attr'){
                var evObj = goPrm['attr'];
                $(tag).attr(evObj);
                /*var list = evObj.
                 for(var i = 0;i < list.length;i++){

                 }*/
            }
        }
    }

    function Go(prm,list,tag,returnOBJ){
        for(var i = 0;i < list.length;i++){
            var prop = list[i];
            var goTag;
            var goPrm = prm[prop];
            //console.log(goPrm);

            if(prop != '$'){
                var repeat = 1;
                var eachFN = null;
                if(goPrm != undefined){
                    if(goPrm.$ != undefined){
                        if(goPrm.$.repeat != undefined){
                            if(goPrm.$.repeat.length != undefined){
                                repeat = goPrm.$.repeat.length;
                                eachFN = goPrm.$.repeat.each;
                            }
                        }
                    }
                }else{

                }

                var dot = prop.indexOf('.');
                var $returnOBJ = {};

                for(var j = 0;j < repeat;j++){
                    if(dot == -1){
                        goTag = tag.appendChild(document.createElement(prop));
                        returnOBJ[prop] = $returnOBJ;
                        $returnOBJ['$element'] = goTag;
                    }else{
                        var name = prop.substr(0,dot);
                        var clases = prop.substr(dot+1,prop.length).split('.');
                        goTag = name = document.createElement(name);
                        tag.appendChild(name);
                        name.className  = clases.join(' ');
                        returnOBJ[clases.join('_')] = $returnOBJ;
                        $returnOBJ['$element'] = goTag;
                        //console.log(name,clases);
                    }

                    if(goPrm != undefined){
                        var goList = Object.keys(goPrm);
                        if(goList.length != 0){
                            Go(goPrm,goList,goTag,$returnOBJ);
                        }
                    }

                    if(eachFN){
                        eachFN(goTag,j);
                    }
                }
            }else{
                $func(tag,goPrm);
            }
        }
    }

    var rOBJ = {};
    for(var a = 0;a < length;a++){
        Go(obj,Object.keys(obj),parent,rOBJ);
    }
    return rOBJ;
}