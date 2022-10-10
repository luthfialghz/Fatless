## Fatless API

This API is intended for the purposes of our project at Hackaton Finance and Health

## Table of Contents

* [Routes](#routes)
* [API Documentation](#api-documentation)
* [Contributor](#contributor)

## Routes

*Still work on progress*
| HTTP METHOD | POST            | GET       | PUT         | DELETE |
| ----------- | :-------: | :------:  | :------:  | :------: |
| /predict    | Prediction Bodyfat Percentage | - | - | - |


## API Documentation 
*Still work on progress*
### List of Endpoints
* [Predict](### Predict)



## Prediction

### Predict
* Method : POST
* URL : `/predict`    
* Request body :
```json 
[
    {
        "Density": 1.0708,
        "Age": 25,
        "Weight": 154.25,
        "Height": 67.75,
        "Chest": 93.1,
        "Abdomen": 85.2,
        "Hip": 94.5,
        "Thigh": 59,
        "Biceps": 32
    }
]
```
* Response body:
```json
{
    "Prediction": 12.246999999999982
}
``` 

## Contributor

1. Luthfi Yafi Alfiansyah
