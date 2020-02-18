using System.Collections;
using System.Collections.Generic;
using UnityEngine;


/// <summary>
/// this is the shield class
/// </summary>
///

public class Shield : MonoBehaviour
{
    [Header("Set in Inspector")]
    public float rotationPerSecond = 0.1f;//the shield rotation animation speed

    [Header("set Dynamically")]
    public int levelShown = 0;//the shield level is being shown
    

    Material mat;//the material reference of the shield

    // Use this for initialization
    void Start()//the start method that assigns the materials to the shield
    {
        mat = GetComponent<Renderer>().material;
        print(Hero.S.shieldLevel);
    }

    // Update is called once per frame
    void Update()//called every frame, in charge of checking and modifying the level of the shield
    {
        int currLevel = Mathf.FloorToInt(Hero.S.shieldLevel);
        

        if (levelShown != currLevel)
        {
            levelShown = currLevel;

            mat.mainTextureOffset = new Vector2(0.2f * levelShown, 0);

        }

        float rZ = -(rotationPerSecond * Time.time * 360) % 360f;
        transform.rotation = Quaternion.Euler(0, 0, rZ);
    }
}