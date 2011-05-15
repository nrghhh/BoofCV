/*
 * Copyright 2011 Peter Abeles
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gecv.alg.tracker.pklt;

import gecv.alg.tracker.klt.KltConfig;
import gecv.struct.image.ImageBase;


/**
 * @author Peter Abeles
 */
// todo comment
public class PkltManagerConfig<I extends ImageBase, D extends ImageBase> {
	public KltConfig config;
	public int maxFeatures = 100;
	public int minFeatures = 80;
	public int featureRadius = 3;

	// if in sequential mode the feature descriptions are updated after every cycle
	public boolean sequentialMode;

	public int imgWidth;
	public int imgHeight;

	public int pyramidScaling[];

	public Class<I> typeInput;
	public Class<D> typeDeriv;


}