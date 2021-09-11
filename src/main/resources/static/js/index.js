let userId = getUrlParameter('userId')

if(userId == null || userId == '') {
    userId = localStorage.getItem('userId')
    if (userId == null || userId == '') {
        document.getElementById('createUser').value = true
    } else {
//        fetch('/savedPreferences?userId='+userId)
//        .then(response => response.json())
//        .then(jsonResponse => {
//            document.getElementById('camera.Chemcam').
//        })
        window.location.href = '/?userId' + userId
    }
}

if (userId != null && userId != '') {
    localStorage.setItem('userId', userId)
    document.getElementById('userId').value = userId
}

let marsApiButtons = document.querySelectorAll("button[id*='marsApi']")
marsApiButtons.forEach(button => button.addEventListener('click', function() {
                         const buttonId = this.id
                         const roverId = buttonId.split('marsApi')[1]
                         const apiData = document.getElementById('marsRoverName')
                         apiData.value = roverId
                         document.getElementById('formRoverType').submit()
                            }))

let marsRoverType = document.getElementById('marsRoverName').value

highlightButtonByRoverType(marsRoverType)

let marsSol = document.getElementById('marsSol').value
if(marsSol != null && marsSol != '' && marsSol >= 0) {
    document.getElementById('marsSol').value = marsSol
}

function highlightButtonByRoverType(roverType) {
    if(roverType == '')
       roverType = 'Opportunity'
    document.getElementById('marsApi' + roverType).classList.remove('btn-secondary')
    document.getElementById('marsApi' + roverType).classList.add('btn-primary')
}

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}
