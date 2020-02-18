using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// this is the summary
/// </summary>

public class BoundsCheck : MonoBehaviour//a
{
    [Header("Set in Inspector")]
    public float radius = 1f;
    public bool keepOnScreen = true;

    [Header("Set Dynamically")]
    public bool isOnScreen = true;
    public float camWidth;
    public float camHeight;

    [HideInInspector]
    public bool offRight, offLeft, offUp, offDown;
    // Start is called before the first frame update
    void Start()//getting the height of the canvas and the width of the canvas by multiplying the ratio
    {
        camHeight = Camera.main.orthographicSize;
        camWidth = camHeight * Camera.main.aspect;
    }

    // Update is called once per frame
    void LateUpdate()//check if the object is out of either 4 bounds that are on the canvas
    {
        Vector3 pos = transform.position;
        isOnScreen = true;
        offDown = offLeft = offRight = offUp = false;

        if (pos.x > camWidth - radius)
        {
            pos.x = camWidth - radius;
            offRight = true;
        }
        if (pos.x < -camWidth + radius)
		{
            pos.x = -camWidth + radius;
            offLeft = true;
        }
        if (pos.y > camHeight - radius)
		{
            pos.y = camHeight - radius;
            offUp = true;
        }
        if (pos.y < -camHeight + radius)
        {
            pos.y = -camHeight + radius;
            offDown = true;
        }
        isOnScreen = !(offDown || offLeft || offRight || offUp);
        if (keepOnScreen && !isOnScreen)
		{
            transform.position = pos;
            isOnScreen = true;
            offDown = offLeft = offRight = offUp = false;
        }
        
    }
    void OnDrawGizmos()/*To see boundaries of the camera view in the screen pane use OnDrawGizmos as below.
To see the boundary when the game is running, the component needs to be selected.This
will allow for checking that enemies are destroyed as soon as they exit the camera view.*/
    {
        if (!Application.isPlaying) return;
        Vector3 boundSize = new Vector3(camWidth * 2, camHeight * 2, 0.1f);
        Gizmos.DrawWireCube(Vector3.zero, boundSize);
    }

}
