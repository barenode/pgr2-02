package maze;

import java.util.List;

public class Intersect {
	private static final float SMALL_NUM = .00000001f;
	
	public static float perp(float x1, float y1, float x2, float y2) {
		return x1 * y2 - y1 * x2;				
	}
	
	public static float perp(Vec2D u, Vec2D v) {
		return perp(u.x, u.y, v.x, v.y);			
	}
	
	public static boolean inSegment(Vec2D p, Vec2D sp0, Vec2D sp1)
	{
	    if (sp0.x != sp1.x) {    // S is not  vertical
	        if (sp0.x <= p.x && p.x <= sp1.x)
	            return true;
	        if (sp0.x >= p.x && p.x >= sp1.x)
	        	return true;
	    } else {    // S is vertical, so test y  coordinate
	        if (sp0.y <= p.y && p.y <= sp1.y)
	        	return true;
	        if (sp0.y >= p.y && p.y >= sp1.y)
	        	return true;
	    }
	    return false;
	}
	
	public static Vec2D intersect(
		Vec2D s1p0, 
		Vec2D s1p1, 
		Vec2D s2p0, 
		Vec2D s2p1)
	{
		Vec2D u = s1p1.sub(s1p0);
		Vec2D v = s2p1.sub(s2p0);
		Vec2D w = s1p0.sub(s2p0);
	    float D = perp(u,v);

	    Vec2D i0;
	    
	    // test if  they are parallel (includes either being a point)
	    if (Math.abs(D) < SMALL_NUM) {           // S1 and S2 are parallel
	        if (perp(u,w) != 0 || perp(v,w) != 0)  {
	            return null;                    // they are NOT collinear
	        }
	        // they are collinear or degenerate
	        // check if they are degenerate  points
	        float du = u.dot(u);
	        float dv = v.dot(v);
	        if (du==0 && dv==0) {            // both segments are points
	            if (!s1p0.equals(s2p0)) {        // they are distinct  points
	            	return null;
	            }
	            i0 = s1p0;                 // they are the same point
	            return i0;
	        }
	        if (du==0) {                     // S1 is a single point
	            if (!inSegment(s1p0, s2p0, s2p1)) { // but is not in S2
	            	return null;
	            }
	            i0 = s1p0;
	            return i0;
	        }
	        if (dv==0) {                     // S2 a single point
	            if  (!inSegment(s2p0, s1p0, s1p1)) {  // but is not in S1
	            	return null;
	            }
	            i0 = s2p0;
	            return i0;
	        }
	        // they are collinear segments - get  overlap (or not)
	        float t0, t1;                    // endpoints of S1 in eqn for S2
	        Vec2D w2 = s1p1.sub(s2p0);
	        if (v.x != 0) {
                 t0 = w.x / v.x;
                 t1 = w2.x / v.x;
	        }
	        else {
                 t0 = w.y / v.y;
                 t1 = w2.y / v.y;
	        }
	        if (t0 > t1) {                   // must have t0 smaller than t1
	        	float t=t0; t0=t1; t1=t;    // swap if not
	        }
	        if (t0 > 1 || t1 < 0) {
	            return null;      // NO overlap
	        }
	        t0 = t0<0? 0 : t0;               // clip to min 0
	        t1 = t1>1? 1 : t1;               // clip to max 1
	        if (t0 == t1) {                  // intersect is a point
	        	i0 = s2p0.add(v.mul(t0));
	            return i0;
	        }
	        // they overlap in a valid subsegment
	        i0 = s2p0.add(v.mul(t0));
	        //*I1 = S2.P0 + t1 * v;
	        return i0;
	    }
	    // the segments are skew and may intersect in a point
	    // get the intersect parameter for S1
	    float sI = perp(v,w) / D;
	    if (sI < 0 || sI > 1)                // no intersect with S1
	        return null;

	    // get the intersect parameter for S2
	    float     tI = perp(u,w) / D;
	    if (tI < 0 || tI > 1) {               // no intersect with S2
	    	return null;
	    }
	    i0 = s1p0.add(u.mul(sI));                // compute S1 intersect point
	    return i0;
	}
	
	public static float dist(Vec2D p0, Vec2D p1) {
		float x = p1.x - p0.x;
		float y = p1.y - p0.y;
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public static Vec2D collide(float posX, float posY, float newPosX, float newPosY, List<Segment> segments) {		
		Vec2D p0 = new Vec2D(posX, posY);
		Vec2D p1 = new Vec2D(newPosX, newPosY);
		
		Vec2D dest = p1;		
		float dist = dist(p0, dest);
		
		Vec2D intersect = null;
		boolean changed = false;
		
		for (Segment segment : segments) {
			intersect = intersect(p0, dest, segment.p0, segment.p1);
			if (intersect!=null) {		
				float intersectDist = dist(p0, intersect);
				if (intersectDist<dist) {
					changed = true;
					dest = intersect;
					dist = intersectDist;
				}
			}
		}		
		float finalX, finalY;
		float offset = .0001f;
		if (changed) {
			if (dest.x < posX) {
				finalX = dest.x + offset;
			} else {
				finalX = dest.x - offset;
			}
			if (dest.y < posY) {
				finalY = dest.y + offset;
			} else {
				finalY = dest.y - offset;
			}
			return new Vec2D(finalX, finalY);
		} else {
			return dest;
		}
	}
}
